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
        sh 'mv build/libs/sepomex-0.0.1-SNAPSHOT.war .'
        sh 'mv sepomex-0.0.1-SNAPSHOT.war ROOT.war'
      }
    }

    stage('Build image docker') {
      steps{
        script {
          docker.withTool('Docker') {
            docker.withRegistry('https://752822034914.dkr.ecr.us-east-1.amazonaws.com/sepomex', 'ecr:us-east-1:techminds-aws') {
              def customImage = docker.build("sepomex:${env.VERSION}", '--build-arg URL_WAR=ROOT.war --build-arg FILE_NAME_CONFIGURATION=application-PRODUCTION.yml --build-arg PATH_NAME_CONFIGURATION=/root/.sepomex/ .')
              customImage.push()
            }
          }
        }
      }
    }
    /*

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
