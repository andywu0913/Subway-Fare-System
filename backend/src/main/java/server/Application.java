package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import server.domain.TicketTransactionMessage;

@SpringBootApplication
public class Application {
  @Autowired
  private TicketTransactionService ticketTransactionService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topics}")
  public void kafkaTicketTxListener(TicketTransactionMessage ticketTxMsg) throws InterruptedException {
    System.out.println("Received ticket transaction: %s".formatted(ticketTxMsg));

    switch (ticketTxMsg.getMethod()) {
      case "enter":
        ticketTransactionService.enter(ticketTxMsg);
        break;
      case "exit":
        ticketTransactionService.exit(ticketTxMsg);
        break;
      default:
        System.err.println("Ticket transaction method illegal: %s".formatted(ticketTxMsg.getMethod()));
        break;
    }
  }
}
