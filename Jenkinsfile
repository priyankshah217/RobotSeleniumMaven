pipeline {
  agent any
  stages {
    stage('Fetch from github') {
      steps {
        git(url: 'https://github.com/priyankshah217/RobotSeleniumMaven.git', branch: 'master')
      }
    }
    stage('Print Env') {
      steps {
        sh 'echo $ABC'
      }
    }
  }
  environment {
    ABC = getRandomString()
  }
}

def getRandomString(){
  return "Priyank from Method"
}