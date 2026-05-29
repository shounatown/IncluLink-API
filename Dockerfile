FROM maven:3-openjdk-24 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:24-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]