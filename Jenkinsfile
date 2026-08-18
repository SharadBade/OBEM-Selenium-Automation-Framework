pipeline {
    agent any

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge'],
            description: 'Browser used for Selenium execution'
        )
        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
        )
        string(
            name: 'BASE_URL',
            defaultValue: 'https://obem.example.com',
            description: 'OBEM environment URL'
        )
        choice(
            name: 'SUITE',
            choices: ['regression', 'smoke'],
            description: 'TestNG suite to execute'
        )
    }

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Clean & Compile') {
            steps {
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Execute Smoke / Regression') {
            steps {
                script {
                    def suiteFile = params.SUITE == 'smoke' ? 'testng-smoke.xml' : 'testng.xml'
                    bat "mvn test -Dsurefire.suiteXmlFiles=${suiteFile} -Dbrowser=${BROWSER} -Dheadless=${HEADLESS} -DbaseUrl=${BASE_URL}"
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
        success {
            echo 'OBEM automation pipeline completed successfully.'
        }
        failure {
            echo 'OBEM automation pipeline failed. Review screenshots, logs and reports.'
        }
    }
}
