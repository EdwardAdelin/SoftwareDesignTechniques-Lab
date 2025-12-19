# Start with a lightweight Java 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# Set a volume for temporary files (optional but good practice for Spring Boot)
VOLUME /tmp

# Copy the compiled jar file from the target folder into the container
# Note: This assumes you have only one jar in target.
COPY target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java","-jar","/app.jar"]