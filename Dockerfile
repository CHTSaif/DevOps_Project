FROM openjdk:11-jdk-slim

EXPOSE 8084

COPY target/DevopsApp.jar DevopsApp.jar


ENTRYPOINT ["java","-jar","/DevopsApp.jar"]



