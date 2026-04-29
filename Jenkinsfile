pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Utilities') {
            steps {
                dir('flipkart-utilities') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('flipkart-automation-project') {
                    sh 'mvn test'
                }
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'flipkart-automation-project/test-output',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Test Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed'
        }
        failure {
            echo 'Tests failed'
        }
        success {
            echo 'All tests passed'
        }
    }
}