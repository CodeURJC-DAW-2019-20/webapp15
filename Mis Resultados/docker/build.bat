cd ..

cd ./frontend/frontend

docker run --rm --name angular-container -v "%cd%":/angular -w /angular node:12.16.1 /bin/bash -c "npm install; npm run-script build"

xcopy /E "%cd%"\dist\frontend cd ..\..\backend\src\main\resources\static\new

cd ../../backend/

docker run --rm -v "%cd%":/usr/src/project -w /usr/src/project maven:alpine mvn package

cd ../

docker image build -t fase4 -f docker/Dockerfile .
