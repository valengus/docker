FROM jenkins/jenkins:lts-centos7-jdk11

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

ENV ADMIN_USER="admin"
ENV ADMIN_PASSWORD="password"

RUN jenkins-plugin-cli --plugins \
    configuration-as-code:1.53 \
    job-dsl:1.77 \
    workflow-aggregator:2.6 \
    git:4.8.2 \
    locale:1.4 \
    ssh-agent:1.23 \
    ssh-slaves:1.33.0 \
    ws-cleanup:0.39 \
    docker-plugin:1.2.3 \
    docker-workflow:1.26 \
    ansible:1.1 \
    parameterized-scheduler:1.0 \
    git-parameter:0.9.13

ENV CASC_JENKINS_CONFIG="/usr/local/jenkins-config-as-code.yaml"
COPY jenkins-config-as-code.yaml /usr/local/jenkins-config-as-code.yaml

EXPOSE 8080

ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]