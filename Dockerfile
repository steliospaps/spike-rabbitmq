FROM java:openjdk-8-jre
COPY rabbitmq-spike/target/rabbitmq-spike-1.0-SNAPSHOT-jar-with-dependencies.jar /app.jar
CMD ["java","-jar","/app.jar"]