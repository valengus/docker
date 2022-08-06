#! /bin/bash -e

echo JENKINS_HOME $JENKINS_HOME
echo http_port $http_port

exec /usr/bin/java -Dcasc.jenkins.config=${JENKINS_HOME}/casc_configs -Djenkins.install.runSetupWizard=false -jar /usr/share/java/jenkins.war --webroot=/var/cache/jenkins/war --httpPort=${http_port} "$@"