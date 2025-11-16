# -------- Stage 1: Build --------
FROM gradle:8.7-jdk21 AS builder

# Directorio de trabajo
WORKDIR /app

# Copiar todo
COPY . .

# Dar permisos al wrapper
RUN chmod +x gradlew

# Build completo, saltando tests y validateStructure para evitar errores
RUN ./gradlew build -x test -x validateStructure --no-daemon

# -------- Stage 2: Runtime --------
FROM eclipse-temurin:21-jdk-alpine

# Crear usuario no root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Volumen temporal para Spring Boot
VOLUME /tmp

# Copiar el JAR generado desde el stage de build
COPY --from=builder /app/applications/app-service/build/libs/franchise.jar franchise.jar

# Variables de entorno para JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Ejecutar como usuario no root
USER appuser

# expose port
EXPOSE 8080

# Comando para iniciar la app
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar franchise.jar" ]
