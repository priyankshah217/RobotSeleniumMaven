pipeline {
  agent any
  stages {
    stage('Fetch from github') {
      steps {
        git(url: 'https://github.com/priyankshah217/RobotSeleniumMaven.git', branch: 'master')
      }
    }
  }
  environment {
    ABC = 'Priyank'
  }
}