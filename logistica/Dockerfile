# --- 1. Etapa de Construcción (Build Stage) ---
# Usamos una imagen de Maven con Java 21 para compilar el proyecto.
# El alias "build" nos permitirá referirnos a esta etapa más adelante.
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero los archivos de configuración de Maven para aprovechar el caché de Docker.
# Si el pom.xml no cambia, Docker no volverá a descargar las dependencias.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargamos todas las dependencias del proyecto
RUN ./mvnw dependency:go-offline

# Copiamos el resto del código fuente de la aplicación
COPY src ./src

# Compilamos la aplicación y generamos el archivo .jar. Omitimos los tests para acelerar la construcción.
RUN ./mvnw package -DskipTests

# --- 2. Etapa de Ejecución (Run Stage) ---
# Partimos de una imagen ligera que solo contiene el entorno de ejecución de Java (JRE).
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos únicamente el archivo .jar generado en la etapa de construcción.
COPY --from=build /app/target/*.jar app.jar

# El comando para ejecutar la aplicación cuando el contenedor se inicie.
ENTRYPOINT ["java", "-jar", "app.jar"]
