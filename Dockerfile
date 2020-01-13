FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine-slim AS builder
WORKDIR /app

COPY . .

RUN apk add --no-cache maven && \
    mvn package
    
FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine-slim

COPY --from=builder /app/target/*.jar /app/app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

#ENTRYPOINT ["/bin/sh"]

ENTRYPOINT ["java"]
CMD ["-jar", "/app/app.jar"]
