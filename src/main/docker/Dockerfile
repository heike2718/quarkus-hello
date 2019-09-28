FROM openjdk:11

MAINTAINER Heike Winkelvoß "public@egladil.de"

WORKDIR /usr/local/bin/quarkus-hello

COPY *-runner.jar ./app.jar
COPY lib ./lib

# mount as /home/drpwzrd/docker-volumes/checklisten
VOLUME /usr/local/bin/quarkus-hello/config

EXPOSE 9000

CMD ["java","-jar","-Dquarkus.datasource.password=hwinkel","app.jar"]
