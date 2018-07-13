package beans.models.webservices;


public class FindTickets {
    private String userEmail;
    private long eventId;

    public FindTickets() {
    }

    public FindTickets(String userEmail, long eventId) {
        this.userEmail = userEmail;
        this.eventId = eventId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}