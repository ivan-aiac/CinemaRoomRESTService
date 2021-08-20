package aiac.cinema;

import org.springframework.http.HttpStatus;

public class CinemaRoomRequestException extends RuntimeException {
    private final HttpStatus status;

    public CinemaRoomRequestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
