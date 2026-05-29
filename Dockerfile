# Usamos una imagen base oficial de Ubuntu para tener el control total
FROM ubuntu:24.04 AS build

# Instalamos las herramientas necesarias: OpenJDK 24 y Maven
RUN apt-get update && apt-get install -y \
    openjdk-24-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Copiamos el código del proyecto al contenedor
COPY . .

# Compilamos el archivo JAR saltándonos los tests
RUN mvn clean package -DskipTests

# --- Etapa de Ejecución (Imagen más ligera) ---
FROM ubuntu:24.04

# Instalamos solo el entorno de ejecución de Java 24 para que no pese tanto
RUN apt-get update && apt-get install -y \
    openjdk-24-jre \
    && rm -rf /var/lib/apt/lists/*

# Copiamos el JAR generado en la etapa anterior
COPY --from=build /target/*.jar app.jar

# Exponemos el puerto de tu API
EXPOSE 8081

# Comando para arrancar IncluLink
ENTRYPOINT ["java", "-jar", "/app.jar"]