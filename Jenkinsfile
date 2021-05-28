#!/usr/bin/env groovy
pipeline {
    agent any

    stages {
        stage("Checkout") {
            steps {
                git branch: 'main', changelog: true, poll: true, url: 'https://github.com/ViktoriaShamuradova/jenkins'
            }
        }
        stage("Build, tests") {
            steps {
                script {
                    bat './gradlew clean build codeCoverageReport'
                }
            }
        }
        stage("SonarQube") {
            steps {
                script {
                    bat 'gradle sonarqube'
                }
            }
        }
        stage("Deploy") {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat_credentials',
                        path: '', url: 'http://localhost:8088/')],
                        contextPath: '', war: '**/*.war'
            }
        }
    }
}