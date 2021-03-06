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
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
      steps{
        echo 'Building app'
        sh 'gradle clean build -x test'
      }
    }

    stage('Download Config'){
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
      steps{
        dir("configFiles"){
          sh "git clone -b ${env.BRANCH_NAME} --single-branch git@bitbucket.org:techmindsmx/sepomex.git ."
        }
      }
    }

    stage('Preparing build Image Docker'){
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
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
      when {
        expression {
          env.BRANCH_NAME in ["master","stage","production"]
        }
      }
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

    stage('Deploy Docker Machine development') {
      when {
        expression {
          env.BRANCH_NAME == "master"
        }
      }
      steps{
        sh "ssh ec2-user@34.206.149.172 sh /home/ec2-user/deployApps.sh ${env.VERSION} development sepomex 8084 8080"
      }
    }

    stage('Deploy Docker Machine stage') {
      when {
        expression {
          env.BRANCH_NAME == "stage"
        }
      }
      steps{
        sh "ssh ec2-user@34.206.149.172 sh /home/ec2-user/deployApps.sh ${env.VERSION} stage sepomex 8085 8080"
      }
    }

    stage('Deploy Docker Machine production') {
      when {
        expression {
          env.BRANCH_NAME == "production"
        }
      }
      steps{
        sh "ssh ec2-user@34.206.149.172 sh /home/ec2-user/deployApps.sh ${env.VERSION} production sepomex 8086 8080"
      }
    }

  }

  post {
    always {
      cleanWs()
    }
  }
}
