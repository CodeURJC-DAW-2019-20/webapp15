FROM openjdk:8-alpine

COPY ./backend/target/web_ejem1-0.1.0-SNAPSHOT.jar ./misresultados.jar

COPY ./backend/src/main/resources/static/images ./images

CMD ["java", "-jar", "misresultados.jar"]
