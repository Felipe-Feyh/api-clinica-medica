#!/bin/bash

CONTAINER_NAME="mysql_container"
MYSQL_ROOT_PASSWORD="admin"
MYSQL_DATABASE="clinica"
MYSQL_USER="admin"
MYSQL_PASSWORD="admin"

# Verifica se o contêiner já está em execução
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "O contêiner $CONTAINER_NAME já está em execução."
else
    # Verifica se o contêiner existe, mas não está em execução
    if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
        echo "O contêiner $CONTAINER_NAME existe, mas não está em execução. Iniciando o contêiner..."
        docker start $CONTAINER_NAME
    else
        # Cria e inicia um novo contêiner MySQL
        echo "Criando e iniciando um novo contêiner MySQL..."
        docker run -d \
            --name $CONTAINER_NAME \
            -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
            -e MYSQL_DATABASE=$MYSQL_DATABASE \
            -e MYSQL_USER=$MYSQL_USER \
            -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
            -p 3306:3306 \
            mysql:latest
    fi
fi
