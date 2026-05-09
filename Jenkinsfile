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
                sh 'mvn clean install'
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
                sh '''
                    PID=$(lsof -t -i:8080)
            
                    if [ ! -z "$PID" ]; then
                        kill -9 $PID
                    fi
            
                    exit 0
                    '''
            }
        }
        stage('Deploy JAR') {
            steps {
                sh '''
                nohup java -jar target/ROOT.jar > /tmp/logs/captcha-console.log 2>&1 &
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