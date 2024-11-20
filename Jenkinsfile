pipeline {
    agent any

    environment {
        GOOGLE_CREDENTIALS = credentials('f58955a2-dd67-47bb-93da-ef2c82463923') // Reference the ID of your credentials
    }

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'staging', 'prod'], description: 'Select the environment to deploy')
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository from GitHub
                git branch: 'main', url: 'https://github.com/MabuSubani1803/jenkins-build-deploy.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the project using Maven
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Set Environment Variables') {
            steps {
                script {
                    if (params.ENVIRONMENT == 'dev') {
                        // Set environment variables for dev environment
                        env.GCP_PROJECT = 'your-dev-project-id'
                        env.GCP_REGION = 'us-central1'
                    } else if (params.ENVIRONMENT == 'staging') {
                        // Set environment variables for staging environment
                        env.GCP_PROJECT = 'heroic-gantry-441405-b3'
                        env.GCP_REGION = 'us-central1'
                    } else if (params.ENVIRONMENT == 'prod') {
                        // Set environment variables for prod environment
                        env.GCP_PROJECT = 'heroic-gantry-441405-b3'
                        env.GCP_REGION = 'us-central1'
                    }
                    echo "Deploying to ${params.ENVIRONMENT} environment"
                }
            }
        }

        stage('Deploy to App Engine') {
            steps {
                script {
                    // Authenticate with Google Cloud using the service account credentials
                    withCredentials([file(credentialsId: 'f58955a2-dd67-47bb-93da-ef2c82463923', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        // Authenticate with Google Cloud SDK
                        sh '''
                            gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                            gcloud config set project $GCP_PROJECT
                            # Deploy to App Engine (no need to set region)
                            gcloud app deploy --quiet
                        '''
                    }
                }
            }
        }
    }
}
