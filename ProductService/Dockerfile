FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} productserivce.jar

ENTRYPOINT ["java", "-jar", "/productserivce.jar"]

EXPOSE 8081