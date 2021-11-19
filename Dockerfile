FROM maven:3-openjdk-11 AS build

WORKDIR /usr/src/app
COPY . .

RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre

ENV WAR_FILE=users-api.war
EXPOSE 8080

WORKDIR /opt/app

COPY --from=build /usr/src/app/target/$WAR_FILE .

ENTRYPOINT java -jar \
  -Dspring.datasource.username="$(cat /run/secrets/db-user)" \
  -Dspring.datasource.password="$(cat /run/secrets/db-password)" \
  $WAR_FILE
