FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

ADD target/student-info-manager.jar student-info-manager.jar

ENTRYPOINT ["java","-jar","student-info-manager.jar"]