package aiac.cinema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {

    private UUID token;
    @JsonProperty(value = "ticket")
    private final Seat seat;
    @JsonIgnore
    private boolean available;

    @JsonCreator
    public Ticket(@JsonProperty("token") UUID token) {
        this.token = token;
        seat = null;
    }

    public Ticket(Seat seat) {
        this.seat = seat;
        token = null;
        available = true;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void purchased() {
        token = UUID.randomUUID();
        available = false;
    }

    public void refunded() {
        token = null;
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }
}
