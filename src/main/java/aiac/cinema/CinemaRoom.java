package aiac.cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CinemaRoom {
    private final int totalRows;
    private final int totalColumns;
    private final List<Ticket> tickets;

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        tickets = new ArrayList<>(totalColumns * totalRows);
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++){
                tickets.add(new Ticket(new Seat(i, j, i <= 4 ? 10 : 8)));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return tickets.stream()
                .filter(Ticket::isAvailable)
                .map(Ticket::getSeat)
                .collect(Collectors.toList());
    }

    public Ticket purchaseTicket(Seat seat) {
        if (seat.getColumn() <= 0 || seat.getRow() <= 0 || seat.getColumn() > totalColumns || seat.getRow() > totalRows) {
            throw CinemaRoomExceptions.OUT_OF_BOUNDS.get();
        }
        int index = (seat.getRow() - 1) * totalColumns + seat.getColumn() - 1;
        Ticket ticket = tickets.get(index);
        if (ticket.isAvailable()) {
            ticket.purchased();
            return ticket;
        } else {
            throw CinemaRoomExceptions.NOT_AVAILABLE.get();
        }
    }

    public Map<String,Seat> refundTicket(Ticket refundedTicket) {
        Optional< Ticket> foundTicket = tickets.stream()
                .filter(ticket -> refundedTicket.getToken().equals(ticket.getToken()))
                .findFirst();
        if (foundTicket.isPresent()) {
            foundTicket.get().refunded();
            return Map.of("returned_ticket", foundTicket.get().getSeat());
        } else {
            throw CinemaRoomExceptions.WRONG_TOKEN.get();
        }
    }

    public Stats calculateStatistics(String password) {
        if ("super_secret".equals(password)) {
            List<Ticket> purchasedTickets = tickets.stream()
                    .filter(Predicate.not(Ticket::isAvailable))
                    .collect(Collectors.toList());
            int income = purchasedTickets.stream()
                    .mapToInt(ticket -> ticket.getSeat().getPrice())
                    .sum();
            return new Stats(income, totalRows * totalColumns - purchasedTickets.size(), purchasedTickets.size());

        } else {
            throw CinemaRoomExceptions.WRONG_PASSWORD.get();
        }
    }
}
