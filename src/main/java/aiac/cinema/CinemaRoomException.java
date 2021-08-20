package aiac.cinema;

public class CinemaRoomException {

    private final String error;

    public CinemaRoomException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
