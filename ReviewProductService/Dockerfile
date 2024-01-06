FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} reviewproductservice.jar

ENTRYPOINT ["java", "-jar", "/reviewproductservice.jar"]

EXPOSE 8085