FROM maven:3.8.4-openjdk-17 as build

ARG DB_HOST
ARG DB_PORT
ARG DB_PASSWORD
ARG DB_USERNAME
ARG DB_NAME

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /usr/src/app/target/*.jar /application.jar
#COPY --from=build /usr/src/app/target/classes/img /img
#COPY --from=build /usr/src/app/target/classes/fonts /fonts
#COPY --from=build /usr/src/app/target/classes/docs /docs
WORKDIR .

EXPOSE 8080
CMD ["java","-jar","/application.jar"]