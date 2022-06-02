package server;

import org.springframework.data.jpa.repository.JpaRepository;

import server.domain.TicketTransactionEntity;

public interface TicketTransactionRepository extends JpaRepository<TicketTransactionEntity, Integer> {
}
