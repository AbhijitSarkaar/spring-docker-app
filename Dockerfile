
FROM eclipse-temurin:25

WORKDIR /root

COPY ./target/*.jar /root/dockerapp.jar

CMD ["java", "-Dspring.profiles.active=dev", "-jar", "dockerapp.jar"]
