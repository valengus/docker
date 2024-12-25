import jenkins.model.Jenkins
import com.cloudbees.hudson.plugins.folder.*
import org.jenkinsci.plugins.workflow.job.WorkflowJob

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

// Get list of all files from var dir ends with '.pkrvars.hcl'
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
  job = folder.createProject(WorkflowJob, it.image)
}