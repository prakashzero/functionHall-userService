# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Environment variables will be passed at runtime from GitHub Actions
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_SECURITY_USER_NAME=${SPRING_SECURITY_USER_NAME}
ENV SPRING_SECURITY_USER_PASSWORD=${SPRING_SECURITY_USER_PASSWORD}

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
