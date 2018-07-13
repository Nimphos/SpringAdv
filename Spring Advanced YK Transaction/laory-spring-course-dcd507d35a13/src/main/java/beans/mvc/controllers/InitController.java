package beans.mvc.controllers;

import beans.models.*;
import beans.services.*;
import beans.transaction.dao.UserAccountDAO;
import beans.transaction.models.UserAccount;
import beans.transaction.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class InitController {

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping("/init")
    @ResponseBody
    String init() {
        userService.register(new User(1,"yevhen.kovalenko@gmail.com", "Yevhen Kovalenko", LocalDate.now(), "123", "ADMIN"));
        System.out.println("Data is created");
        return "Data is created";
    }


    @RequestMapping("/initAll")
    @ResponseBody
    String initAll(){

        String email = "yevhen.kovalenko@gmail.com";
        String name = "Yevhen Kovalenko";
        String password = "123";
        String role = "REGISTERED_USER,BOOKING_MANAGER,ADMIN";
        String eventName1 = "SOLO: A STAR WARS STORY";
        String eventName2 = "OVERBOARD";
        String eventName3 = "SHOW DOGS";
        String eventName4 = "AVENGERS: INFINITY WAR";
        String eventName5 = "BOOK CLUB";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        userService.register(new User(email, name, LocalDate.of(1986, 3, 9), password, role));
        userService.register(new User("user1@gmail.com", "John", LocalDate.of(1990, 4, 10), "1", "REGISTERED_USER, BOOKING_MANAGER"));
        userService.register(new User("user2@gmail.com", "Martin", LocalDate.of(1992, 5, 11), "1", "REGISTERED_USER"));
        //userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29)));

        User userByEmail = userService.getUserByEmail(email);
        System.out.println("User with email: [" + email + "] is " + userByEmail);
        System.out.println();

        System.out.println("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);
        System.out.println();

        Event event1 = eventService.create(
                new Event(eventName1, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                        blueHall));
        System.out.println();
        System.out.println("Event by name: " + eventService.getByName(event1.getName()));
        System.out.println();
        Event event2 = eventService.create(new Event(eventName2, Rate.HIGH, 60, dateOfEvent, blueHall));
        Event event3 = eventService.create(
                new Event(eventName3, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        blueHall));
        eventService.create(
                new Event(eventName4, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                        redHall));
        Event event = new Event(eventName5, Rate.HIGH, 150,
                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall);
        event = eventService.create(event);

        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        System.out.println(
                "Discount for user: [" + email + "] for event: [" + eventName1 + "] in auditorium: [" + auditoriumName +
                        "] on date: [" + dateOfEvent + "] is [" +
                        discountService.getDiscount(userByEmail, eventService.getEvent(eventName1, blueHall, dateOfEvent))
                        + "]");
        System.out.println();

        //eventService.remove(event2);
        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), seats, userByEmail);
        System.out.println("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event2, LocalDateTime.now(), seats2, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats2,
                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event3, LocalDateTime.now(), seats3, userByEmail,
                bookingService.getTicketPrice(event.getName(),
                        event.getAuditorium().getName(),
                        event.getDateTime(), seats3,
                        userByEmail)));


        System.out.println();
        System.out.println("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                event.getAuditorium().getName(),
                event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> System.out.println("" + i + ") " + ticketsForEvent.get(i)));

        System.out.println();
        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName1);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                userByEmail);

        UserAccount userAccount = new UserAccount(userByEmail, 500.00);
        userAccountService.create(userAccount);
        List<UserAccount> userAccountList = userAccountService.getAll();
        for(UserAccount account : userAccountList){
            System.out.println(account);
        }
        UserAccount userAccountByEmail = userAccountService.getByUserEmail(email);
        System.out.println(userAccountByEmail);

        return "'Init all' process is done";
    }

}
