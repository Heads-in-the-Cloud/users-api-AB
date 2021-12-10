#!groovy
pipeline {
    agent any

    environment {
        image_label = "ab-users-microservice"
        git_commit_hash ="${sh(returnStdout: true, script: 'git rev-parse HEAD')}"
        image = ""
    }

    stages {
        stage('Package JAR file') {
            steps {
                sh "./mvnw clean package"
            }
        }

        stage('Build image') {
            steps {
                script {
                    image = docker.build image_label
                }
            }
        }

        stage('Push image to registry') {
            steps {
                script {
                    docker.withRegistry(USERS_ECR_URI, 'ecr:us-west-2:ecr-creds') {
                        image.push("$git_commit_hash")
                        image.push('latest')
                    }
                }
            }
        }

        stage('Remove image') {
            steps {
                sh "docker rmi $image_label"
            }
        }
    }
}

