FROM oraclelinux:9

ARG JENKINS_VERSION=2.346-1.1
ARG JENKINS_HOME=/var/lib/jenkins
ARG http_port=8080

RUN dnf install -y java-11-openjdk ; \
    curl -o /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo ; \
    rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key ; \
    dnf -y install jenkins-${JENKINS_VERSION} ; \
    dnf clean all

COPY jenkins.sh /usr/local/bin/jenkins.sh

ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]