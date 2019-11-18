pipeline {
  agent any
  stages {
    stage('Fetch from github') {
      steps {
        git(url: 'https://github.com/priyankshah217/RobotSeleniumMaven.git', branch: 'master')
      }
    }
    stage('Run Test') {
      steps {
        sh 'mvn clean test'
      }
    }
  }
  environment {
    ABC = 'Priyank'
  }
}