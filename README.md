# quarkus-hello
spielprojekt zum lernen

## current version

* __1.4.1__ switched from jsonb to jackson-databind, newer versions of my commos-libs


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

[JWT-Protected](https://mathe-jung-alt.de/quarkus-hello/secured/admins)

[public all users](https://mathe-jung-alt.de/quarkus-hello/secured/public)

[send testmail](https://mathe-jung-alt.de/quarkus-hello/mail)