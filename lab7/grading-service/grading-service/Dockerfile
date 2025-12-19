# Stage 1: build with Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
# copy the service module only (adjust paths if necessary)
COPY src ./src
RUN mvn -B -DskipTests package

# Stage 2: run the app
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /workspace/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]