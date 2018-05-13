FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD demo-diit-server-eureka-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8501