FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} reviewcompanyservice.jar

ENTRYPOINT ["java", "-jar", "/reviewcompanyservice.jar"]

EXPOSE 8086