#!/usr/bin/env groovy

node {
   stage('checkout') {
       checkout scm
   }
   
   stage('Installing Packages') {
      sh "java -version"
   }

   stage('packaging') {
       sh "mvn -U clean package -DskipTests"
   }

   //stage('sonar quality analysis') {
   //     withSonarQubeEnv('My SonarQube Server') {
   //         sh "mvn sonar:sonar \
  //-Dsonar.projectKey=com:nsdl:book-appointment \
  //-Dsonar.host.url=http://172.16.11.166:9000 \
  //-Dsonar.login=f8c5c42fa3e8578c0046e239f34c9e8c5b2736f3"
  //       }
  //  }

  // stage('Unit Test') {
  //     sh "mvn test"
  // }

   def bookappointmentVersion
   stage ('create Image Version'){
   bookappointmentVersion = sh (
   script: "date +'%Y-%m-%d-%H.%M'",
   returnStdout: true,
      )
   }

   def dockerImage
   stage('build docker') {
       sh "cp -R docker target/"
       sh "cp target/*.jar target/docker/"
       dockerImage = docker.build('telemed/bookappointmentservice', 'target/docker')
   }

   stage('publish docker') {
       docker.withRegistry('http://172.16.11.166:37719', 'telemed') {
           dockerImage.push "${bookappointmentVersion}"
         }
      }
      
   stage('delete docker Image') {
       sh "docker rmi telemed/bookappointmentservice"
       sh "docker rmi 172.16.11.166:37719/telemed/bookappointmentservice:${bookappointmentVersion}"
       }

   stage('Deploy on Dev Env') {
         sshagent (credentials: ['nsdl']) {
       sh "echo 'bookappointmentVersion=${bookappointmentVersion}' > .env"
       sh "scp -o StrictHostKeyChecking=no .env mosip@172.30.151.100:/var/workspace/telemedicine"
       sh "ssh -o StrictHostKeyChecking=no -l mosip 172.30.151.100 /var/workspace/telemedicine/helper/bookappointment.sh"
         }
       }              
       
   }

