# Use a Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
# RUN ./mvnw clean install 
#This command skips the Build TESTCASE which we used to avoid DB Conncection check.
# chmod +x mvnw
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
# -DskipTests

# Use a JDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ARG DB_USERNAME=root
ARG DB_PASSWORD=root
ARG DB_PORT=3306
ARG DB_NAME=my_pg
ARG DB_URL=jdbc:mysql://mysql:${DB_PORT}/${DB_NAME}
ARG PORT=5555

# Set the environment variables for the application
ENV DB_URL=${DB_URL}
ENV DATASOURCE_USERNAME=${DB_USERNAME}
ENV DATASOURCE_PASSWORD=${DB_PASSWORD}
ENV DB_PORT=${DB_PORT}
ENV DB_NAME=${DB_NAME}
ENV PORT=${PORT}

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
