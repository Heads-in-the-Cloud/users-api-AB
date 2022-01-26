#!groovy
pipeline {
    agent any

    parameters {
        string(
            name: 'ProjectId',
            defaultValue: 'utopia-AB',
            description: 'Identifier applied to all names'
        )
    }

    environment {
        COMMIT_HASH = sh(
            script: "git rev-parse --short=8 HEAD",
            returnStdout: true
        ).trim()
        REGION = sh(
            script:'aws configure get region',
            returnStdout: true
        ).trim()
        AWS_ACCOUNT_ID = sh(
            script:'aws sts get-caller-identity --query "Account" --output text',
            returnStdout: true
        ).trim()

        ecr_uri = "${AWS_ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com"
        image_label = "users-microservice-${params.ProjectId.toLowerCase()}"
        image = null
        packaged = false
        built = false
    }

    stages {
        stage('Package') {
            steps {
                sh "./mvnw package"
                sh "docker context use default"
            }

            post {
                success {
                    script {
                        packaged = true
                    }
                }
            }
        }

        //stage('SonarQube Analysis') {
        //    steps {
        //        withSonarQubeEnv('SonarQube') {
        //            sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        //        }
        //    }
        //}

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
                    docker.withRegistry(
                        "http://$ecr_uri/$image_label",
                        "ecr:$REGION:jenkins"
                    ) {
                        image.push("$COMMIT_HASH")
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
