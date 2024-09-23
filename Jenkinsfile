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
                          -Dsonar.host.url=http://localhost:9000 \
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
            script {
                echo "$TEXT_SUCCESS_BUILD"
            }
        }
        failure {
            script {
                echo "$TEXT_FAILURE_BUILD"
            }
        }
    }
}
