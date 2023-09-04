pipeline {
 agent any
 stages {
     stage ("SCM"){
        steps {  git 'https://github.com/pandian3k/hippo.git'} 
     }
     stage ("BUILD"){
        steps { bat label: '', script: 'mvn clean'
bat label: '', script: 'mvn install' } 
     }
     stage ("DEPLOY"){
        steps { bat label: '', script: 'xcopy /y "C:\\Program Files (x86)\\Jenkins\\workspace\\pipe\\pipline\\target\\hippo.war" "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps"'       } 
     }
 }
}
