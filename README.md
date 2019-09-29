# quarkus-hello
spielprojekt zum lernen


## docker image bauen

docker image build -t heik2718/quarkus-hello .

docker image build -t heik2718/minikaenguru-mariadb .

## start db-container

this ist secret

## connect to db-container

docker container exec -it minikaenguru_minikaenguru-database_1 bash

## tail the log

docker logs -f minikaenguru_quarkus-hello_1
