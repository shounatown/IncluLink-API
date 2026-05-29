FROM maven:3.9.6-eclipse-temurin-24-noble AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]