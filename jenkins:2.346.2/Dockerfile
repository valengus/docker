FROM jenkins/jenkins:2.346.2-lts-centos7

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

ENV ADMIN_USER="admin"
ENV ADMIN_PASSWORD="password"

RUN jenkins-plugin-cli --plugins \
    git-parameter \
    configuration-as-code \
    job-dsl \
    workflow-aggregator \
    git \
    locale \
    ssh-agent \
    ssh-slaves \
    ws-cleanup \
    docker-plugin \
    docker-workflow

ENV CASC_JENKINS_CONFIG="/usr/local/jenkins-config-as-code.yaml"
COPY jenkins-config-as-code.yaml /usr/local/jenkins-config-as-code.yaml

EXPOSE 8080

ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]
