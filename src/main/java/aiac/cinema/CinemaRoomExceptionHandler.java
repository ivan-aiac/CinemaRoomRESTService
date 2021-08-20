package aiac.cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class CinemaRoomExceptionHandler {

    @ExceptionHandler(value = {CinemaRoomRequestException.class})
    public ResponseEntity<CinemaRoomException> handleCinemaRoomRequestException(CinemaRoomRequestException exception) {
        CinemaRoomException payload = new CinemaRoomException(exception.getMessage());
        return new ResponseEntity<>(payload, exception.getStatus());
    }
}
