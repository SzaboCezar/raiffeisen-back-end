# Folosim o imagine de bază cu Java 11 și Gradle instalat
FROM gradle:8.5-openjdk-19 as build

# Setăm directorul de lucru în interiorul containerului
WORKDIR /app

# Copiem fișierele de proiect în directorul de lucru al containerului
COPY . .

# Executăm comanda 'gradle build' pentru a construi aplicația
RUN gradle build

# Folosim o imagine de bază Java 11 pentru a rula aplicația
FROM openjdk:19

# Setăm directorul de lucru în interiorul containerului
WORKDIR /app

# Copiem fișierul JAR al aplicației din etapa anterioară
COPY --from=builder /app/build/libs/raiffeisen-backend-project.jar app.jar

# Exemplu pentru expunerea unui port
EXPOSE 8080

# Comanda pentru rularea aplicației Spring Boot în container
CMD ["java", "-jar", "app.jar"]
