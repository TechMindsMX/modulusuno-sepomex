pipeline {
  agent any

  tools {
    gradle "Gradle 2.10"
  }

  environment {
    VERSION = "${UUID.randomUUID().toString().replace('-','')[0..6]}" 
  }

  stages {

    stage('Build App') {
      steps{
        echo 'Building app'
        sh 'gradle clean build -x test'
      }
    }

    stage('Download Config'){
      steps{
        dir("configFiles"){
          //sh "git clone -b ${env.BRANCH_NAME} --single-branch git@bitbucket.org:techmindsmx/sepomex.git ."
          sh "git clone -b master --single-branch git@bitbucket.org:techmindsmx/sepomex.git ."
        }
      }
    }

    stage('Preparing build Image Docker'){
      steps{
        sh 'cp configFiles/application-PRODUCTION.yml .'
        dir("folderDocker"){
          sh "git clone git@github.com:makingdevs/Tomcat-Docker.git ."
        }
        sh 'mv folderDocker/* .'
        sh 'ls .'
        //sh 'mv build/libs/app.jar .'
      }
    }

    /*stage('Build image docker') {
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
      steps{
        script {
          docker.withTool('Docker') {
            docker.withRegistry('https://752822034914.dkr.ecr.us-east-1.amazonaws.com/emailer', 'ecr:us-east-1:techminds-aws') {
              def customImage = docker.build("emailer:${env.VERSION}", '--build-arg URL_WAR=app.jar --build-arg FILE_NAME_CONFIGURATION=conf.json --build-arg PATH_NAME_CONFIGURATION=/root/emailer/ .')
              customImage.push()
            }
          }
        }
      }
    }

    stage('Deploy Kube') {
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
      environment {
        ENVIRONMENT = "${env.BRANCH_NAME == 'master' ? 'development' : env.BRANCH_NAME}"
      }
      steps{
        sh "ssh ec2-user@34.200.152.121 sh /home/ec2-user/deployApp.sh ${env.VERSION} ${env.ENVIRONMENT}"
      }
    }*/

  }

  post {
    always {
      cleanWs()
    }
  }
}
