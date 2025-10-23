FROM maven:latest
LABEL authors="ottopar"

WORKDIR /app

COPY pom.xml /app
COPY src /app/src/

RUN mvn package

CMD ["java", "-cp", "target/OTP2-1-1.0-SNAPSHOT.jar", "ShoppingCartTest"]