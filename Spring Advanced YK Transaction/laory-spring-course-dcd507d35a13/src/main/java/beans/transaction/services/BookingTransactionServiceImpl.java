package beans.transaction.services;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.transaction.exceptions.LimitExceedException;
import beans.transaction.models.UserAccount;
import beans.transaction.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingTransactionServiceImpl implements BookingTransactionService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private BookingService bookingService;

    @Override
    public Ticket bookTickets(User user, Event event, List<Integer> seats){


        boolean userHasEnoughMoney = withdrawFromDeposit(user, event, seats);

        Ticket ticket = null;

        if(userHasEnoughMoney) {
            ticket = bookingService.bookTicket(user, new Ticket(event, LocalDateTime.now(), seats, user,
                    bookingService.getTicketPrice(event.getName(),
                            event.getAuditorium().getName(),
                            event.getDateTime(), seats,
                            user)));
        }
        return ticket;
    }

    private boolean withdrawFromDeposit(User user, Event event, List<Integer> seats) throws LimitExceedException {
        double totalPrice = 0.0;
        UserAccount userAccount = userAccountService.getByUserEmail(user.getEmail());
        double ticketPrice = event.getBasePrice();
        List<Integer> vipSeatsList = event.getAuditorium().getVipSeatsList();
        for(Integer seat : seats){
            if(vipSeatsList.contains(seat)){
                totalPrice += ticketPrice*0.2;
            }
            else{
                totalPrice += ticketPrice;
            }
        }
        if(totalPrice > userAccount.getDeposit()){
            throw new LimitExceedException("User doesn't have enough money to buy tickets!");
        }
        userAccount.setDeposit(userAccount.getDeposit() - totalPrice);
        userAccountService.update(userAccount);
        return true;
    }
}
