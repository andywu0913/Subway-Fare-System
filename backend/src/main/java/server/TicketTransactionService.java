package server;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.domain.TicketTransactionEntity;
import server.domain.TicketTransactionEntity.ProcessingStatus;
import server.domain.TicketTransactionMessage;

@Service
public class TicketTransactionService {
    @Autowired
    private TicketTransactionRepository ticketTransactionRepository;

    public void enter(TicketTransactionMessage ticketTxMsg) {
        TicketTransactionEntity ticketTx = new TicketTransactionEntity();
        try {
            ticketTx.setMethod(ticketTxMsg.getMethod());
            ticketTx.setStationID(ticketTxMsg.getStationID());
            ticketTx.setTicketID(ticketTxMsg.getTicketID());
            ticketTx.setTimestamp(new Timestamp(ticketTxMsg.getTimestamp()));

            persistTicketTransaction(ticketTx, ProcessingStatus.PROCESSING);

            // mock some other business logic
            Thread.sleep(1000);

            persistTicketTransaction(ticketTx, ProcessingStatus.COMPLETE);

        } catch (Exception e) {
            System.err.println("Failed to process ticket transaction for entering gate: %s".formatted(e));
            persistTicketTransaction(ticketTx, ProcessingStatus.FAILED);
        }
    }

    public void exit(TicketTransactionMessage ticketTxMsg) {
        TicketTransactionEntity ticketTx = new TicketTransactionEntity();
        try {
            ticketTx.setMethod(ticketTxMsg.getMethod());
            ticketTx.setStationID(ticketTxMsg.getStationID());
            ticketTx.setTicketID(ticketTxMsg.getTicketID());
            ticketTx.setTimestamp(new Timestamp(ticketTxMsg.getTimestamp()));

            persistTicketTransaction(ticketTx, ProcessingStatus.PROCESSING);

            // mock some other business logic
            Thread.sleep(1000);

            persistTicketTransaction(ticketTx, ProcessingStatus.COMPLETE);

        } catch (Exception e) {
            System.err.println("Failed to process ticket transaction for exiting gate: %s".formatted(e));
            persistTicketTransaction(ticketTx, ProcessingStatus.FAILED);
        }
    }

    private void persistTicketTransaction(TicketTransactionEntity ticketTx, ProcessingStatus status) {
        try {
            ticketTx.setProcessingSataus(status);
            ticketTransactionRepository.save(ticketTx);
        } catch (Exception e) {
            System.err.println("Failed to persist ticket transaction record: %s".formatted(e));
        }
    }
}
