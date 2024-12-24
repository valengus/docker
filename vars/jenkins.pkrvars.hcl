base_image = "local/jdk17:latest"
image_name = "jenkins"
tags       = ["latest", "2.479.2"]
changes = [
  "ENV LANG=\"C.UTF-8\"",

  "ENV JENKINS_HOME=\"/var/lib/jenkins\"",
  "ENV JENKINS_WAR=\"/usr/share/java/jenkins.war\"",
  "ENV JAVA_OPTS=\"-Djenkins.install.runSetupWizard=false \"",
  "ENV JENKINS_PORT=\"8080\"",
  "ENV CASC_JENKINS_CONFIG=\"/var/lib/jenkins/jenkins-config-as-code.yaml\"",

  "ENV JENKINS_ADMIN_USER=\"admin\"",
  "ENV JENKINS_ADMIN_PASS=\"password\"",
  "ENV JENKINS_DEBUG_LEVEL=\"5\"",

  "EXPOSE 8080",
  "USER jenkins",
  "WORKDIR /var/lib/jenkins",

  "CMD [\"/usr/bin/jenkins\"]",
]
