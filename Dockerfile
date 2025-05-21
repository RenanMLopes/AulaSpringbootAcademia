FROM amazoncorretto:17-alpine

RUN mkdir /app

WORKDIR /app

COPY ./target/aula-0.0.1-SNAPSHOT.jar .

CMD ("java", "-jar", "aula-0.0.1-SNAPSHOT.jar")