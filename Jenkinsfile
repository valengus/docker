pipeline {
  agent any

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
          sh '/usr/bin/packer init oraclelinux9.pkr.hcl'
      }
    }

    stage('Build') {
      steps {
          sh '/usr/bin/packer build oraclelinux9.pkr.hcl'
      }
    }

    stage('Test') {
      steps {
          echo 'Test'
      }
    }

    stage('Push') {
      steps {
          echo 'Push'
      }
    }


  }
}
