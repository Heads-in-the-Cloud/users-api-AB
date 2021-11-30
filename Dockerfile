FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

ENV JAR_FILE=users-api.jar

COPY target/$JAR_FILE .

EXPOSE 8080

ENTRYPOINT java -jar $JAR_FILE
