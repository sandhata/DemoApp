# Pull base image 
From tomcat:8-jre8 

# Maintainer 
MAINTAINER "hipposupport" 
COPY ./hippo.war /usr/local/tomcat/webapps
