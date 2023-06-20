# Define la imagen base de Java 8
FROM openjdk:8-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/api-notas-1.0.0.jar /app/app.jar

# Expone el puerto en el que se ejecuta tu aplicación
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["java", "-jar", "app.jar"]
