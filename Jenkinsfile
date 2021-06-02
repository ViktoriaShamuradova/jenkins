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
                
                def tries = 0
                sonarResultStatus = "PENDING"
                while ((sonarResultStatus == "PENDING" || sonarResultStatus == "IN_PROGRESS") && tries++ < 5) {
                    try {
                        sonarResult = waitForQualityGate abortPipeline: true
                        sonarResultStatus = sonarResult.status
                    } catch(ex) {
                        echo "caught exception ${ex}"
                    }
                    echo "waitForQualityGate status is ${sonarResultStatus} (tries=${tries})"
                }
                if (sonarResultStatus != 'OK') {
                    error "Quality gate failure for SonarQube: ${sonarResultStatus}"
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