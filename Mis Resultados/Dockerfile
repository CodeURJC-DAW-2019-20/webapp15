FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8443 available to the wordocd outside this container
EXPOSE 8443

ADD ./target/web_ejem1-0.1.0-SNAPSHOT.jar web_ejem1-0.1.0-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/web_ejem1-0.1.0-SNAPSHOT.jar"]