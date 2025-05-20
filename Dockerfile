FROM maven:3.9.4-eclipse-temurin-21 as  build
LABEL authors="Steve Tchoumi"
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /build/target/todo-app-*.jar /app/todo-app.jar

EXPOSE 8080
CMD ["java", "-jar", "todo-app.jar"]