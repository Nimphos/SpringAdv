package beans.services;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Ticket getTicketById(long id);

    List<Ticket> getTicketsByUser(User user);

    List<Ticket> getTicketsByEvent(Event event);

    double getTicketPrice(String event, String auditorium, LocalDateTime dateTime, List<Integer> seats, User user);

    Ticket bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date);

    List<Ticket> getTicketsForEventByUserEmail(String userEmail, long eventId);
}
