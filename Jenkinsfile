pipeline{
    agent any

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
    }
    post {
        always {
            // Post-build actions (e.g., clean up, notifications)
            echo 'Pipeline finished.'
        }
    }
}