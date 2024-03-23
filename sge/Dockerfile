# Etapa de compilação para o aplicativo Spring Boot
FROM openjdk:17-jdk-slim AS springboot

WORKDIR /appback/backend

COPY sge ./

RUN ./mvnw package -DskipTests

# Etapa final de construção
FROM openjdk:17-jdk-slim

WORKDIR /appback

# Copie o JAR do aplicativo Spring Boot da etapa anterior
COPY --from=springboot /appback/backend/target/*.jar app.jar

# Defina o comando para iniciar o aplicativo Spring Boot
CMD ["java", "-jar", "app.jar"]