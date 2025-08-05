#1Use an official Java runtime as the base image
 FROM openjdk:17-jdk-slim


#2Set the woring directory inside the container
 WORKDIR /app

#3Copy yhe built JAR file into the container
COPY target/deutsche-springboot-demo-0.0.1-SNAPSHOT.jar app.jar


#4Expose the applicaction port
 EXPOSE 8080

#5Run the application
 ENTRYPOINT ["java", "-jar", "app.jar"]