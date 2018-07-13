package beans.services;

import beans.models.Auditorium;
import beans.models.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    public Event create(Event event);

    public void remove(Event event);

    Event getEvent(String name, Auditorium auditorium, LocalDateTime dateTime);

    public Event getById(long id);

    public List<Event> getByName(String name);

    public List<Event> getAll();

    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    public List<Event> getNextEvents(LocalDateTime to);

    public Event assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);
}
