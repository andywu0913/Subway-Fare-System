# Mock Subway Fare System

This is a practice of familiarizing the Java Spring Boot framework and Kafka. The `gate-client/` is a Node.js application representing the speed gate end device that publishes every swipe message of the ticket to Kafka when entering/exiting the gate. The `backend/` is a Java Spring Boot application that acts as the Kafka consumer that processes the swipe messages, does some operations, and finally persists the records into MySQL.

## gate-client

- Before starting the gate-client application, setup the configurations in the `config/` folder first.
- Run `npm ci` to install the dependencies.
- Run `npm start` to start the gate-client application.

### APIs

There are two available HTTP APIs we can use:

#### Enter Gate

```bash
curl -X POST 'http://localhost:8900/enter' \
--header 'Content-Type: application/json' \
--data-raw '{
    "stationID": "TPE-01",
    "ticketID": "26d4-f87c-46a2-b010"
}'
```

#### Exit Gate

```bash
curl -X POST 'http://localhost:8900/exit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "stationID": "TAO-01",
    "ticketID": "26d4-f87c-46a2-b010"
}'
```

Now we have produced some swipe messages to Kafka brokers.

## backend

- Before starting the backend application, setup the configuration file `src/main/java/resources/application.properties` first.
- Run `./mvnw spring-boot:run` to start the backend application.
- Finally, we can see that the queued messages have been processed and stored into MySQL.

## Techstack

- [x] Node.js + Kafka producer
- [x] Java Spring Boot + Kafka consumer
- [x] MySQL with connection pool
- [ ] Kafka authentication with SASL
