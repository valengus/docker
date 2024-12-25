import jenkins.model.*
import hudson.model.*
import hudson.triggers.*
import com.cloudbees.hudson.plugins.folder.*
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.*

  
  
def gitUrl = "https://github.com/valengus/docker.git"

Jenkins jenkins = Jenkins.instance 

 // Jenkins folder for docker jobs
def folder = jenkins.getItem("docker")
// Create the folder if it doesn't exist
if (folder == null) {
  folder = jenkins.createProject(Folder.class, "docker")
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

  // Jenkinsfile
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
      // stage('Build') {
      //   steps {
      //     sh "/usr/bin/packer build -var-file=vars/${it.image}.pkrvars.hcl -only=\'*.docker.build\' docker.pkr.hcl"
      //   }
      // }
      // stage('Test') {
      //   steps {
      //     echo 'Test'
      //   }
      // }
      // stage('Push') {
      //   steps {
      //     sh "/usr/bin/packer build -var-file=vars/${it.image}.pkrvars.hcl -only=\'docker.push\' docker.pkr.hcl"
      //   }
      // }
    }
  }
  """

  // Создаем новый job типа Pipeline
  def job = folder.createProject(WorkflowJob, it.image)
  // Устанавливаем содержание Jenkinsfile
  def flowDefinition = new CpsFlowDefinition(jenkinsfileContent, true)
  job.definition = flowDefinition

  
  // Сохраняем изменения
  job.save()
}

// // Trigers
// imageList.each { it ->

//   if (it.dependsOn != null) {
//     // println("Переменная не равна null")
//     def triggeringJobName = it.dependsOn
//     def triggeredJobName = it.image

//   } else {
//     // println("Переменная равна null")
//   }

// }