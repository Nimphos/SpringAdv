package beans.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Date;

@XmlType(propOrder = { "id", "name", "rate", "basePrice", "dateTime", "auditorium" }, name = "event")
@XmlRootElement
public class Event {

    @XmlElement(required = true)
    private long          id;
    @XmlElement(required = true)
    private String        name;
    @XmlElement(required = true, type = String.class)
    private Rate          rate;
    @XmlElement(required = true)
    private double        basePrice;
    @XmlElement(required = true, type = Date.class)
    private LocalDateTime dateTime;
    @XmlElement(required = true)
    private Auditorium    auditorium;

    public Event() {
    }

    public Event(String name, Rate rate, double basePrice, LocalDateTime dateTime, Auditorium auditorium) {
        this(-1, name, rate, basePrice, dateTime, auditorium);
    }

    public Event(long id, String name, Rate rate, double basePrice, LocalDateTime dateTime, Auditorium auditorium) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.basePrice = basePrice;
        this.dateTime = dateTime;
        this.auditorium = auditorium;
    }

    public Event withId(Long eventId) {
        return new Event(eventId, this.name, this.rate, this.basePrice, this.dateTime, this.auditorium);
    }

    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @XmlTransient
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @XmlTransient
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @XmlTransient
    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Event))
            return false;

        Event event = (Event) o;

        if (id != event.id)
            return false;
        if (Double.compare(event.basePrice, basePrice) != 0)
            return false;
        if (name != null ? !name.equals(event.name) : event.name != null)
            return false;
        if (rate != event.rate)
            return false;
        if (dateTime != null ? !dateTime.equals(event.dateTime) : event.dateTime != null)
            return false;
        return auditorium != null ? auditorium.equals(event.auditorium) : event.auditorium == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (auditorium != null ? auditorium.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", rate=" + rate +
               ", basePrice=" + basePrice +
               ", dateTime=" + dateTime +
               ", auditorium=" + auditorium +
               '}';
    }
}
