FROM eclipse-temurin:23-jdk AS build
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline  
COPY src src
RUN ./mvnw clean package -DskipTests