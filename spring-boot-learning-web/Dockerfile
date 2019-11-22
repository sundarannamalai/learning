FROM openjdk:8-jre
MAINTAINER Sundar Annamalai <sundarannamalai@gmail.com>

VOLUME /tmp

ARG JAR_FILE

ADD ${JAR_FILE} app.war

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]