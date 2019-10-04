# quarkus-hello
spielprojekt zum lernen

## current version
1.3.0


## docker image bauen

docker image build -t heik2718/quarkus-hello .

docker image build -t heik2718/minikaenguru-mariadb .

## start db-container

this ist secret

## connect to db-container

docker container exec -it minikaenguru_minikaenguru-database_1 bash

## tail the log

docker logs -f minikaenguru_quarkus-hello_1

## URLs

[Hello World](https://mathe-jung-alt.de/quarkus-hello/hello/world)

[Statistics](https://mathe-jung-alt.de/quarkus-hello/statistics)

[Secret](https://mathe-jung-alt.de/quarkus-hello/secret)