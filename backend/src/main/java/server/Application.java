package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import server.domain.TicketTransaction;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topics}")
  public void kafkaTicketTxListener(TicketTransaction ticketTx) throws InterruptedException {
    System.out.println(ticketTx);
    System.out.println(ticketTx.getMethod());
    System.out.println(ticketTx.getStationID());
    System.out.println(ticketTx.getTicketID());
    System.out.println(ticketTx.getTimestamp());
  }
}
