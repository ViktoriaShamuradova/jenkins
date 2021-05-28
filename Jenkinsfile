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
                deploy adapters: [tomcat9(credentialsId: '4cb373a7-e745-4feb-8267-07e8d867311f',
                        path: '', url: 'http://localhost:8088/')],
                        contextPath: '', war: '**/*.war'
            }
        }
    }
}