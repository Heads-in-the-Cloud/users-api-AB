#!groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "./mvnw clean package"
                sh "docker build . -t austinbaugh/utopia-users-microservice:${env.BUILD_ID}"
            }
        }

        stage('Run detached for 30sec') {
            steps {
                sh """
                docker run -d \
                    --rm \
                    --name users-microservice \
                    --env DB_URL=${env.DB_URL} \
                    --env DB_USERNAME=${env.DB_USERNAME} \
                    --env DB_PASSWORD=${env.DB_PASSWORD} \
                    -p 8110:8080 \
                    austinbaugh/utopia-users-microservice:${env.BUILD_ID}
                sleep 30
                """
            }
        }

        stage('Kill') {
            steps {
                sh "docker kill users-microservice"
            }
        }
    }
}

