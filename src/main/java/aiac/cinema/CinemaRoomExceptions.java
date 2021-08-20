package aiac.cinema;

import org.springframework.http.HttpStatus;

public enum CinemaRoomExceptions {
    NOT_AVAILABLE(new CinemaRoomRequestException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!")),
    OUT_OF_BOUNDS(new CinemaRoomRequestException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!")),
    WRONG_TOKEN(new CinemaRoomRequestException(HttpStatus.BAD_REQUEST, "Wrong token!")),
    WRONG_PASSWORD(new CinemaRoomRequestException(HttpStatus.UNAUTHORIZED, "The password is wrong!"));

    private final CinemaRoomRequestException exception;

    CinemaRoomExceptions(CinemaRoomRequestException exception) {
        this.exception = exception;
    }

    public CinemaRoomRequestException get() {
        return exception;
    }
}
