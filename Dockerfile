# Use the official Maven image to build the application
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download project dependencies
COPY pom.xml .
COPY src ./src

# Package the application (this will generate a .jar file)
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built .jar file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the task-service port
EXPOSE 8080

# Set environment variables for R2DBC URL and credentials
ENV SPRING_JDBC_URL=${SPRING_JDBC_URL}
ENV SPRING_JDBC_USERNAME=${SPRING_JDBC_USERNAME}
ENV SPRING_JDBC_PASSWORD=${SPRING_JDBC_PASSWORD}

ENV ACTIVE_PROFILE=${ACTIVE_PROFILE}

# Command to run the application
ENTRYPOINT ["sh", "-c", "while ! nc -z luxury-wear-db 3306 ; do sleep 1; done; java -jar app.jar"]