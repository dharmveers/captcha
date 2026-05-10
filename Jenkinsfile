pipeline{
    agent any

    environment {
        DEPLOYMENT_PATH="/var/captcha"
        LOG_PATH= "/tmp/logs"
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
                PID=$(lsof -t -i:8080 2>/dev/null || true)
        
                if [ -n "$PID" ]; then
                    echo "Stopping process on port 8080: $PID"
                    kill -9 $PID
                else
                    echo "No process running on port 8080"
                fi
                '''
            }
        }
        stage('Copy JAR') {
            steps {
                sh '''
                mkdir -p $DEPLOYMENT_PATH
                cp target/ROOT.jar $DEPLOYMENT_PATH/ROOT.jar

                echo "JAR copied to $DEPLOYMENT_PATH"
                '''
            }
        }
        stage('Deploy JAR') {
            steps {
                sh '''
                mkdir -p $LOG_PATH
                (nohup java -jar $DEPLOYMENT_PATH/ROOT.jar > $LOG_PATH/captcha-console.log 2>&1 &)
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