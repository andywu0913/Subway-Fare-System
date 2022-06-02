package server.domain;

public class TicketTransactionMessage {
  private String method;
  private String stationID;
  private String ticketID;
  private long timestamp;

  public TicketTransactionMessage() {
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

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TicketTransaction [method: %s, stationID: %s, ticketID: %s, timestamp: %d]"
        .formatted(method, stationID, ticketID, timestamp);
  }
}
