FROM maven:3-openjdk-11 AS build

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre

ENV WAR_FILE=users-api.war

WORKDIR /opt/app

COPY --from=build /usr/src/app/target/$WAR_FILE .

ENTRYPOINT java -jar $WAR_FILE
