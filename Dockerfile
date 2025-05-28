# ------------------------------
# Etapa 1: Build con Maven y Java
# ------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Crear directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Dar permisos de ejecución al script mvnw (por si no los tiene)
RUN chmod +x mvnw

# Compilar el proyecto sin correr tests
RUN ./mvnw clean package -DskipTests

# ------------------------------
# Etapa 2: Imagen final más liviana
# ------------------------------
FROM eclipse-temurin:17-jdk

# Crear directorio de trabajo en la imagen final
WORKDIR /app

# Copiar el archivo JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que correrá Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
