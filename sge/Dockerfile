# Stage 1: Build the Spring Boot application
FROM openjdk:17-jdk-slim AS builder

WORKDIR /appback/backend

COPY sge ./

RUN chmod +x mvnw && ./mvnw package -DskipTests

# Stage 2: Run the Spring Boot application
FROM openjdk:17-jdk-slim

WORKDIR /appback

# Copy the Spring Boot jar from the build stage
COPY --from=builder /appback/backend/target/*.jar app.jar

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]

EXPOSE 8080
