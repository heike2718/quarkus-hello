# quarkus-hello
spielprojekt zum lernen

## current version

__1.5.1__

* upgrade to quarkus-1.0.0.CR2
* Cookies-Showcase
* changed authprovider- ClientAccessToken-API


__1.5.0__ upgrade to quarkus-0.26.1

__1.4.2__ CDI fixed. no producers for dependent CDI-Beans

__1.4.1__ switched from jsonb to jackson-databind, newer versions of my commos-libs


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

[Hello World](https://mathe-jung-alt.de/quarkus-hello/hello/world) setzt jetz ein Session-Cookie

[Bye World](https://mathe-jung-alt.de/quarkus-hello/hello/bye) löscht das Session-Cookie

[Statistics](https://mathe-jung-alt.de/quarkus-hello/statistics)

[Secret](https://mathe-jung-alt.de/quarkus-hello/secret)

[JWT-Protected](https://mathe-jung-alt.de/quarkus-hello/secured/admins)

[public all users](https://mathe-jung-alt.de/quarkus-hello/secured/public)

[send testmail](https://mathe-jung-alt.de/quarkus-hello/mail)