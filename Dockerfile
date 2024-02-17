FROM amazoncorretto:21.0.2

ADD ./target/universal/play-scala-rest-api-example-1.0-SNAPSHOT.tgz /app

CMD /app/play-scala-rest-api-example-1.0-SNAPSHOT/bin/play-scala-rest-api-example
