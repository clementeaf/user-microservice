FROM eclipse-temurin:17-jre
VOLUME /tmp
EXPOSE 4500
ADD target/user-management-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]