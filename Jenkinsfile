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
        stage("SonarQube analysis") {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'gradle sonarqube'
                }
            }
        }
        stage("Check quality gate") {
            steps {
               // input message: 'Do you want to approve the deploy in production?', ok: 'Yes'
                def qg = waitForQualityGate ()
                if(qg.status != 'OK') {
                    error "Pipeline aborted to a quality gate failure: ${qg.status}"
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