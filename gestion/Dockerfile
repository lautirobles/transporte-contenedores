# Usa una imagen base con Java 21
FROM eclipse-temurin:21-jdk-jammy

# Copia el archivo JAR compilado de tu aplicación al contenedor
COPY target/*.jar app.jar

# Expone el puerto en el que corre la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación cuando el contenedor inicie
ENTRYPOINT ["java","-jar","/app.jar"]