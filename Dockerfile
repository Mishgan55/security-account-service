
FROM maven:3.8.6-openjdk-17

WORKDIR /app

COPY src src

RUN mvn clean package

COPY --from=build /app/build/libs/*.jar /app/build/libs/app.jar
ENTRYPOINT ["java", "-jar", "/app/build/libs/app.jar"]