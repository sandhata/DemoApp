yum install java-1.8.0-openjdk

java -version

echo $JAVA_HOME

vi  ~/.bash_profile
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-3.b12.el7_3.x86_64/
export JRE_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-3.b12.el7_3.x86_64/jre

source ~/.bash_profile

echo $JAVA_HOME 
