package server;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import server.domain.TicketTransactionEntity;
import server.domain.TicketTransactionMessage;
import server.domain.TicketTransactionEntity.ProcessingStatus;

@SpringBootApplication
public class Application {
  @Autowired
  private TicketTransactionRepository ticketTransactionRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topics}")
  public void kafkaTicketTxListener(TicketTransactionMessage ticketTxMsg) throws InterruptedException {
    System.out.println(ticketTxMsg);

    TicketTransactionEntity ticketTx = new TicketTransactionEntity();
    ticketTx.setProcessingSataus(ProcessingStatus.PROCESSING);
    ticketTx.setMethod(ticketTxMsg.getMethod());
    ticketTx.setStationID(ticketTxMsg.getStationID());
    ticketTx.setTicketID(ticketTxMsg.getTicketID());
    ticketTx.setTimestamp(new Timestamp(ticketTxMsg.getTimestamp()));

    System.out.println(ticketTransactionRepository.save(ticketTx).getID());
  }
}
