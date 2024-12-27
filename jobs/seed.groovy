import jenkins.model.*
import jenkins.triggers.ReverseBuildTrigger
import hudson.model.*
import hudson.triggers.*
import com.cloudbees.hudson.plugins.folder.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.*

def gitUrl = "https://github.com/valengus/docker.git"

Jenkins jenkins = Jenkins.instance 

// Jenkins folder for docker jobs
def folder = jenkins.getItem("docker")
if (folder == null) {
  folder = jenkins.createProject(Folder.class, "docker")
}
// Delete all jobs in "docker" folder
folder.getItems().each { job ->
  try {
    println "Trying to delete ${job.fullName}"
    job.delete()
  } catch (Exception e) {
  }
}

// Clone repo
def mainDir = new File('docker')
mainDir.deleteDir()
def gitCloneCommand = "git clone ${gitUrl}"
def proc = gitCloneCommand.execute()
proc.waitFor()

// Get list of all files in var dir ends with '.pkrvars.hcl'
def pkrvars = []
def imageList = []
def varFolder = new File("docker/vars")

varFolder.eachFileRecurse { it ->
  if (it.name.endsWith('.pkrvars.hcl')) {
    pkrvars.add(it.absolutePath)
  }
}

pkrvars.each { it ->
  new File(it).eachLine { line ->
    image = (it - ~".*vars/" - ~".pkrvars.hcl")
    if (line.contains('base_image')) {
      if (line.contains('local/')) {
        dependsOn = (line - ~".*local/" - ~":.*")
        imageList.add([image: image, dependsOn: dependsOn])
      } else {
        imageList.add([image: image, dependsOn: null])
      }
    }
  }
}

println(imageList)

imageList.each { it ->

  // Jenkinsfile template
  def jenkinsfileContent = """
  pipeline {
    agent any
    environment {
      PKR_VAR_docker_registry = 'docker-registry:5000'
    }
    stages {
      stage('Checkout') {
        steps {
          checkout([
            \$class: 'GitSCM',
            doGenerateSubmoduleConfigurations: false,
            extensions: [[\$class: 'CloneOption', noTags: true, shallow: true, depth: 1, timeout: 30]],
            userRemoteConfigs: [[ url: 'https://github.com/valengus/docker.git' ]],
            branches: [ [name: 'main'] ]
          ])
        }
      }
      stage('Prepare') {
        steps {
          sh '/usr/bin/packer init docker.pkr.hcl'
        }
      }
      stage('Build') {
        steps {
          sh "/usr/bin/packer build -var-file=vars/${it.image}.pkrvars.hcl -only=\'*.docker.build\' docker.pkr.hcl"
        }
      }
      stage('Test') {
        steps {
          sh "/usr/bin/packer build -var-file=vars/${it.image}.pkrvars.hcl -only=\'*.docker.test\' docker.pkr.hcl"
        }
      }
      stage('Trivy') {
        steps {
          sh 'trivy image local/${it.image}:latest'
        }
      }
      stage('Push') {
        steps {
          sh "/usr/bin/packer build -var-file=vars/${it.image}.pkrvars.hcl -only=\'docker.push\' docker.pkr.hcl"
        }
      }
      stage('Info') {
        steps {
          sh "curl -s \${PKR_VAR_docker_registry}/v2/${it.image}/tags/list | python3 -m json.tool"
        }
      }
    }
  }
  """

  // Creating pipline jobs from Jenkinsfile template
  def job = folder.createProject(WorkflowJob, it.image)
  def flowDefinition = new CpsFlowDefinition(jenkinsfileContent, true)
  job.definition = flowDefinition
  // Configuring a trigger
  if (it.dependsOn != null) {
    def trigger = new ReverseBuildTrigger("docker/${it.dependsOn}", Result.SUCCESS)
    job.addTrigger(trigger)  
  }
  // Save
  job.save()
}
