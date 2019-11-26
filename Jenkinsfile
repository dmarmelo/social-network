pipeline {
  agent any
  // Configuration for the variables used for this specific repo
  environment {
    BUILDS_DISCORD = credentials('discord_webhook_url')
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
  }
  /* ######################
   Send status to Discord
   ###################### */
  post {
    always {
      script {
        if (env.EXIT_STATUS == "ABORTED") {
          sh 'echo "build aborted"'
        } else if (currentBuild.currentResult == "SUCCESS") {
          sh ''' curl -X POST -H "Content-Type: application/json" --data '{"avatar_url": "https://wiki.jenkins-ci.org/download/attachments/2916393/headshot.png","embeds": [{"color": 1681177,\
                 "description": "**Build:**  '${BUILD_NUMBER}'\\n**Status:**  Success\\n**Job:** '${RUN_DISPLAY_URL}'\\n"}],\
                 "username": "Jenkins"}' ${BUILDS_DISCORD} '''
        } else {
          sh ''' curl -X POST -H "Content-Type: application/json" --data '{"avatar_url": "https://wiki.jenkins-ci.org/download/attachments/2916393/headshot.png","embeds": [{"color": 16711680,\
                 "description": "**Build:**  '${BUILD_NUMBER}'\\n**Status:**  Failure\\n**Job:** '${RUN_DISPLAY_URL}'\\n"}],\
                 "username": "Jenkins"}' ${BUILDS_DISCORD} '''
        }
      }
    }
  }
}
