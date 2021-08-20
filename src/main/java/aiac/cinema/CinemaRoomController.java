package aiac.cinema;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaRoomController {
    private final CinemaRoom cinemaRoom;

    public CinemaRoomController() {
        this.cinemaRoom = new CinemaRoom(9, 9);
    }

    @GetMapping("/seats")
    public CinemaRoom getAvailableSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Seat seat) {
        return cinemaRoom.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public Map<String, Seat> refundTicket(@RequestBody Ticket ticket) {
        return cinemaRoom.refundTicket(ticket);
    }

    @PostMapping("/stats")
    public Stats getStatistics(@RequestParam(required = false, defaultValue = "") String password) {
        return cinemaRoom.calculateStatistics(password);
    }
}
