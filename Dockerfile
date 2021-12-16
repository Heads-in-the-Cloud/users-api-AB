FROM eclipse-temurin:11-jre-alpine
WORKDIR /opt/app
COPY target/app.jar .
EXPOSE 8080
CMD java -jar app.jar
