FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} categoryservice.jar

ENTRYPOINT ["java", "-jar", "/categoryservice.jar"]

EXPOSE 8088