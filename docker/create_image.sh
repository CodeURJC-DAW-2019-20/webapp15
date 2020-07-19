#!/bin/bash

echo "Building image '$1:$2'"
cp ./Dockerfile ../Mis\ Resultados/
cd ../Mis\ Resultados/
sudo mvn package spring-boot:repackage
sudo docker build -t $1:$2 .
rm ./Dockerfile
sudo docker push alvarorbaneja/daw