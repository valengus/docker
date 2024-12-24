pipeline {
  agent any

  parameters {
    choice(name: 'DOCKER_IMAGE', choices: ['oraclelinux9', 'jdk17', 'jenkins', 'python39'], description: 'Docker image')
  }

  environment {
    PKR_VAR_docker_registry = 'docker-registry:5000'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout([
          $class: 'GitSCM',
          doGenerateSubmoduleConfigurations: false,
          extensions: [[$class: 'CloneOption', noTags: true, shallow: true, depth: 1, timeout: 30]],
          userRemoteConfigs: [[ url: "https://github.com/valengus/docker.git" ]],
          branches: [ [name: "main"] ]
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
        sh '/usr/bin/packer build -var-file=\"vars/${DOCKER_IMAGE}.pkrvars.hcl\" -only=\'*.docker.build\' docker.pkr.hcl'
      }
    }

    stage('Test') {
      steps {
        echo 'Test'
      }
    }

    stage('Push') {
      steps {
        sh '/usr/bin/packer build -var-file=\"vars/${DOCKER_IMAGE}.pkrvars.hcl\" -only=\'docker.push\' docker.pkr.hcl'
      }
    }


  }
}