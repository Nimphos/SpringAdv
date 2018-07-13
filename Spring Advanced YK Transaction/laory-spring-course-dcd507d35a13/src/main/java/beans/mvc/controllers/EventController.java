package beans.mvc.controllers;

import beans.models.Event;
import beans.models.Ticket;
import beans.services.BookingService;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getAllEvents() {
        ModelAndView model = new ModelAndView();
        List<Event> events = eventService.getAll();
        model.addObject("events", events);
        model.setViewName("getAllEvents");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
    public ModelAndView getEventById(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        model.addObject("event", event);
        model.setViewName("event");
        return model;
    }

    @RequestMapping(value = "/events/event/{name}", method = RequestMethod.GET)
    public ModelAndView getEventByName(@PathVariable String name) {
        ModelAndView model = new ModelAndView();
        Event event = (Event) eventService.getByName(name);
        model.addObject("event", event);
        model.setViewName("event");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event.getName(), event.getAuditorium().getName(), event.getDateTime());
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET,
            headers="accept=application/pdf")
    public ModelAndView getBookedTicketsPdf(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event.getName(), event.getAuditorium().getName(), event.getDateTime());
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }
}
