#! /bin/bash -e

exec echo Done "$@"

# exec /usr/bin/java -Dcasc.jenkins.config=${JENKINS_HOME}/casc_configs -Djenkins.install.runSetupWizard=false -jar /usr/share/java/jenkins.war --webroot=/var/cache/jenkins/war --httpPort=${http_port} "$@"