package server.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ticket_transactions")
public class TicketTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @Column(name = "method", columnDefinition = "VARCHAR(16)", nullable = false)
    private String method;

    @Column(name = "station_id", columnDefinition = "VARCHAR(16)", nullable = false)
    private String stationID;

    @Column(name = "ticket_id", columnDefinition = "VARCHAR(32)", nullable = false)
    private String ticketID;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "processing_status", columnDefinition = "TINYINT", nullable = false)
    private ProcessingStatus status;

    public enum ProcessingStatus {
        PENDING, PROCESSING, COMPLETE, FAILED;
    }

    @CreationTimestamp
    @Column(name = "create_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Timestamp updateAt;

    public TicketTransactionEntity() {
        this.status = ProcessingStatus.PENDING;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ProcessingStatus getProcessingSataus() {
        return status;
    }

    public void setProcessingSataus(ProcessingStatus status) {
        this.status = status;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }
}
