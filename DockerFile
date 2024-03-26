FROM openjdk:11-jdk-slim

EXPOSE 8082

COPY target/DevOps_Project.jar DevOps_Project.jar


ENTRYPOINT ["java","-jar","/DevOps_Project.jar"]