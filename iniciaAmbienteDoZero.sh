#!/bin/sh

docker-compose down --rmi all

mvn package -DskipTests=true

docker-compose up
