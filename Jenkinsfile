pipeline{
    agent any

    environment {
        JAR_FILE = "target\\ROOT.jar"
    }
    stages{
        stage('Checkout') {
            steps {
                git branch: 'master', url:  'https://github.com/dharmveers/captcha.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
//        stage('Deploy') {
//            steps {
//                withCredentials([usernamePassword(credentialsId: 'tomcatId', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
//                    bat """
//                                    set "WAR_PATH=%CD%\\target\\ROOT.war"
//                                    set "WAR_PATH=%WAR_PATH:\\=/%%"
//                                    curl -v -u %USERNAME%:%PASSWORD% ^
//                                        "http://127.0.0.1:8080/manager/text/deploy?path=/ROOT&update=true&war=file:/%WAR_PATH%"
//                                """
//                }
//            }
//        }

        stage('Stop Existing App') {
            steps {
                bat '''
                for /F "tokens=5" %%a in ('netstat -aon ^| findstr :8080') do (
                    taskkill /F /PID %%a
                )  
                exit /b 0
                '''
            }
        }
        stage('Deploy JAR') {
            steps {
                powershell '''
                Start-Process java `
                    -ArgumentList "-jar C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\praapti\\target\\ROOT.jar" `
                    -RedirectStandardOutput "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\praapti\\app.log" `
                    -RedirectStandardError "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\praapti\\error.log"
                '''
            }
        }
    }
    post {
        always {
            // Post-build actions (e.g., clean up, notifications)
            echo 'Pipeline finished.'
        }
    }
}