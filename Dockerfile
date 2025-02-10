FROM maven:3.9.9-amazoncorretto-17-alpine

WORKDIR /app

COPY . .

CMD ["mvn", "test", "-P run-api-tests", "allure:report"]