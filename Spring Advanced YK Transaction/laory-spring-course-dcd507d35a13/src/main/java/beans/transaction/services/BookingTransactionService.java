package beans.transaction.services;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingTransactionService {
    Ticket bookTickets(User user, Event event, List<Integer> seats);
}
