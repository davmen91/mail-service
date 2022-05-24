FROM maven:3.8.1-openjdk-11-slim as builder
WORKDIR /wrk/src/
COPY ./mail-service/. /wrk/src/
COPY ./m2/. /root/.m2/
RUN mvn clean
RUN mvn package

FROM openjdk:11.0.10-slim
COPY --from=builder /wrk/src/target/*.jar /app.jar
ENTRYPOINT java -jar /app.jar