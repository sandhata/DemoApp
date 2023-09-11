imageid = ""

pipeline {
	agent any
	environment {
		IMAGE_NAME = "sandhatademoproj"	
	}
	
stages {
	
	stage('Preparation') {
		steps {
            script {
					echo "Preparation"
					cleanWs disableDeferredWipeout: true, deleteDirs: true						
					//checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, quietOperation: true, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "SOURCE"]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GITKEY', url: "https://github.com/sandhata/${params.REPO_NAME}.git"]]]
					checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, quietOperation: true, extensions: [[$class: 'RelativeTargetDirectory']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GITKEY', url: "https://github.com/sandhata/${params.REPO_NAME}.git"]]]							
                }
			}
		}
		stage('Build') {
	            steps { 
					sh label: '', script: 'mvn clean'
					echo "Build"
		            sh label: '', script: 'mvn install'}
		}
		stage('Docker Build') {
			steps {				
				script{	
					echo "Docker build"
					app = docker.build("${IMAGE_NAME}")					
					imageid = sh(script:"docker images -q ${IMAGE_NAME}:${params.TAG_VERSION}", returnStdout: true).trim()
					echo "ImageID - ${imageid}"	
					docker.withRegistry("https://346292482790.dkr.ecr.us-east-2.amazonaws.com", 'ecr:us-east-2:aws-credentials') {                   
						app.push("${params.TAG_VERSION}")							
						app.push("latest")					
                    }					
				}						
			}
		}	
	stage(' EKS Cluster Deploy') {
		steps {
			withAWS(credentials: 'aws-credentials', region: 'us-east-2') 
				{
				script {
					echo "EKS"
					sh 'aws eks update-kubeconfig --name demo-eks-rshyn049 --region us-east-2'
					//sh 'eksctl get cluster'
					sh "cp -Rf ${JENKINS_HOME}/.kube ${WORKSPACE}/."
					//sh "export KUBECTL_HOME=/home/ubuntu/bin/"
					sh "chmod -Rf 777 .kube/"
					sh "cd ${WORKSPACE} && /home/ubuntu/bin/kubectl get svc"
					sh 'cd ${WORKSPACE} && /home/ubuntu/bin/kubectl apply -f deployment.yml'
					sh 'cd ${WORKSPACE} && /home/ubuntu/bin/kubectl apply -f services.yml'
				}	
				}
			}
		}
	}
}
