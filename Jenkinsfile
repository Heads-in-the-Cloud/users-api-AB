#!groovy
pipeline {
    agent any

    environment {
        image_label = "users-microservice-ab"
        commit = sh(returnStdout: true, script: "git rev-parse --short=8 HEAD").trim()
        image = null
        packaged = false
        built = false
    }

    stages {
        stage('Package') {
            steps {
                sh "./mvnw package"
            }

            post {
                success {
                    script {
                        packaged = true
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    image = docker.build(image_label)
                }
            }

            post {
                success {
                    script {
                        built = true
                    }
                }
            }
        }

        stage('Push to registry') {
            steps {
                script {
                    ecr_repo_uri ="https://${ORG_ACCOUNT_NUM}.dkr.ecr.${region}.amazonaws.com/${image_label}"
                    docker.withRegistry(ecr_repo_uri, "ecr:$region:ecr-creds") {
                        image.push("$commit")
                        image.push('latest')
                    }
                }
            }
        }
    }

    post {
        cleanup {
            script {
                if(packaged) {
                    sh "./mvnw clean"

                    if(built) {
                        sh "docker rmi $image_label"
                    }
                }
            }
        }
    }
}



