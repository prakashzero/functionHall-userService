# Build stage using Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY userservice/pom.xml .
COPY userservice/src ./src
RUN mvn clean package -DskipTests

# Run stage using JDK 21
FROM eclipse-temurin:21-jdk
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
