#FROM ubuntu:latest
#LABEL authors="User"

#Use official OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# app jar name
ARG JAR_FILE=target/*.jar

# copy jar file to container
COPY ${JAR_FILE} app.jar

# Expose port 8080 (application.properties에서 설정한 server.port와 맞춰야 함)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]