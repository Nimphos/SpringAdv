package beans.rest.clients;

import beans.models.Event;
import beans.models.Rate;
import beans.models.Ticket;
import beans.models.User;
import beans.models.form.BookForm;
import beans.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class BookingRestClient {

    @Autowired
    private static AuditoriumService auditoriumService;

    private static void getTicketById()
    {
        final String uri = "http://localhost:8080/spring-course-1.0-SNAPSHOT/rest/booking/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        Ticket result = restTemplate.getForObject(uri, Ticket.class, params);

        System.out.println(result);
    }

    private static void getTicketByUser()
    {
        final String uri = "http://localhost:8080/spring-course-1.0-SNAPSHOT/rest/booking/user";

        User user = new User(1, "yevhen.kovalenko@gmail.com", "Yevhen Kovalenko", LocalDate.of(1986, 3, 9));

        RestTemplate restTemplate = new RestTemplate();
        Ticket result = restTemplate.getForObject( uri, Ticket.class, user);

        System.out.println(result);
    }

    private static void getTicketByEvent()
    {
        final String uri = "http://localhost:8080/spring-course-1.0-SNAPSHOT/rest/booking/event";

        Event event = new Event("BOOK CLUB", Rate.HIGH, 150,
                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), auditoriumService.getByName("Yellow hall"));
        RestTemplate restTemplate = new RestTemplate();
        Ticket result = restTemplate.getForObject( uri, Ticket.class, event);

        System.out.println(result);
    }

    private static void createTicket()
    {
        final String uri = "http://localhost:8080/spring-course-1.0-SNAPSHOT/rest/booking";

        BookForm bookForm = new BookForm("SOLO: A STAR WARS STORY", "1,2");

        RestTemplate restTemplate = new RestTemplate();
        Ticket result = restTemplate.postForObject( uri, bookForm, Ticket.class);

        System.out.println(result);
    }

}
