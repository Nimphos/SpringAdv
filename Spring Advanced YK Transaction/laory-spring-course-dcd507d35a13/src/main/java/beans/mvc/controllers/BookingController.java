package beans.mvc.controllers;

import beans.daos.inmemory.InMemoryAuditoriumDAO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.models.form.BookForm;
import beans.models.webservices.FindTickets;
import beans.mvc.services.UploadDataService;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import beans.transaction.exceptions.LimitExceedException;
import beans.transaction.models.UserAccount;
import beans.transaction.service.UserAccountService;
import beans.transaction.services.BookingTransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private BookingTransactionService bookingTransactionService;

    @Autowired
    UploadDataService uploadDataService;

    @Autowired
    private InMemoryAuditoriumDAO inMemoryAuditoriumDAO;


    @RequestMapping(value = "/tickets/book", method = RequestMethod.GET)
    public ModelAndView bookTicket() {
        ModelAndView model = new ModelAndView();

        List<Event> events = eventService.getAll();

        model.addObject("events", events);
        model.addObject("bookForm", new BookForm());
        model.setViewName("book");
        return model;
    }

    @RequestMapping(value = "/tickets/book", method = RequestMethod.POST)
    public String bookTicketProcessing(HttpServletRequest request, Principal principal, BookForm bookForm) throws Exception {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();*/
            String email = request.getUserPrincipal().getName();

            Event event = eventService.getByName(bookForm.getName()).get(0);
            //List<Integer> seats = bookForm.getSeats();
            List<Integer> seats = Arrays.stream(bookForm.getSeatsStr().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            User userByEmail = userService.getUserByEmail(email);

        bookingTransactionService.bookTickets(userByEmail, event, seats);

        return "redirect:/";
    }

    @RequestMapping(value = "/tickets/events/{eventId}", method = RequestMethod.GET)
    public ModelAndView getAllTickets(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event.getName(), event.getAuditorium().getName(), event.getDateTime());
        model.addObject("tickets", tickets);
        model.setViewName("getAllTickets");
        return model;
    }

    @RequestMapping(value = "/tickets/events/{eventId}", method = RequestMethod.GET,
            headers="accept=application/pdf")
    public ModelAndView getAllTicketsPdf(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event.getName(), event.getAuditorium().getName(), event.getDateTime());
        model.addObject("tickets", tickets);
        model.setViewName("getAllTickets");
        return model;
    }

    @RequestMapping(value = "/getBookedTickets", method = RequestMethod.GET)
    public ModelAndView inputUserEmailAndEventId() {
        ModelAndView model = new ModelAndView();
        model.addObject("findTicket", new FindTickets());
        model.setViewName("findTicket");
        return model;
    }

    @RequestMapping(value = "/getBookedTickets", method = RequestMethod.POST)
    public ModelAndView getBookedTickets(HttpServletRequest request, FindTickets findTickets) throws Exception {
        System.out.println("getBookedTickets 1");
        if(findTickets==null)
            throw new Exception("Error Input Data");
        if(findTickets.getUserEmail().equalsIgnoreCase("") || findTickets.getEventId()==0)
            throw new Exception("User and Event should not be empty");

        System.out.println("getBookedTickets 2");

        List<Ticket> tickets = bookingService.getTicketsForEventByUserEmail(findTickets.getUserEmail(), findTickets.getEventId());
        if(tickets==null ||(tickets!=null && tickets.size()==0))
            throw new Exception("Tickets not found!");

        System.out.println("getBookedTickets 3");

        ModelAndView model = new ModelAndView();
        model.addObject("tickets", tickets);
        model.setViewName("ticketsForm");
        return model;
    }

    @RequestMapping(value = "/multipleUpload", method = RequestMethod.GET)
    public String multipleUpload() {
        return "uploadFiles";
    }

    @RequestMapping(value = "/multipleSave", method = RequestMethod.POST)
    public @ResponseBody
    String multipleSave(@RequestParam("files") MultipartFile[] files) {
        String msg = "";
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                msg += uploadDataService.loadDataFromFile(files[i])+ "<br/>";
            }
            return msg;
        } else {
            return "Unable to upload data. File is empty.";
        }
    }
}
