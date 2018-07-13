package beans.rest.controllers;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.models.form.BookForm;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import beans.transaction.services.BookingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingTransactionService bookingTransactionService;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket findTicketById(@PathVariable("id") long id){
        return bookingService.getTicketById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<Ticket> findTicketsByUser(@RequestBody final User user, HttpServletRequest request, HttpServletResponse response){
        return bookingService.getTicketsByUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/event",method = RequestMethod.GET)
    public List<Ticket> findTicketsByEvent(@RequestBody final Event event, HttpServletRequest request, HttpServletResponse response){
        return bookingService.getTicketsByEvent(event);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Ticket bookTicketRest(@RequestBody final BookForm model, HttpServletRequest request, HttpServletResponse response){
        User user = userService.getUserByEmail(request.getUserPrincipal().getName());

        Event event = eventService.getByName(model.getName()).get(0);
        List<Integer> seats = Arrays.stream(model.getSeatsStr().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Ticket result = bookingTransactionService.bookTickets(user, event, seats);

        if(null != result){
            response.setStatus(201);
        }

        return result;
    }
}
