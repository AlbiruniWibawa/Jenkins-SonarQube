pipeline {
    agent any
    environment {
        BUILD_NUMBER_ENV = "${env.BUILD_NUMBER}"
        TEXT_SUCCESS_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Success"
        TEXT_FAILURE_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Failure"
    }

    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=jenkins-sonarqube \
                          -Dsonar.projectName="jenkins-sonarqube" \
                          -Dsonar.host.url=http://sonarqube:9000 \
                          -Dsonar.token=sqp_4eecf3724767b7526246072f8cb28e8f24ec5c0e
                    '''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }
    }

    post {
        success {
            script{
                 withCredentials([string(credentialsId: 'telebot-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id', variable: 'CHAT_ID')]) {
                    sh ' curl -s -X POST https://api.telegram.org/bot"$TOKEN"/sendMessage -d chat_id="$CHAT_ID" -d text="$TEXT_SUCCESS_BUILD" '
                 }
            }
        }
        failure {
            script{
                withCredentials([string(credentialsId: 'telebot-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id', variable: 'CHAT_ID')]) {
                    sh ' curl -s -X POST https://api.telegram.org/bot"$TOKEN"/sendMessage -d chat_id="$CHAT_ID" -d text="$TEXT_FAILURE_BUILD" '
                }
            }
        }
    }
}
