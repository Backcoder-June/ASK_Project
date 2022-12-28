FROM openjdk:11-jdk
ARG WAR_FILE=build/libs/*.war
COPY ${WAR_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]