package beans.models.form;

import java.util.List;

public class BookForm {

    private String name;

    private List<Integer> seats;

    private String seatsStr;

    public BookForm() {
    }

    public BookForm(String name, List<Integer> seats) {
        this.name = name;
        this.seats = seats;
    }

    public BookForm(String name, String seatsStr) {
        this.name = name;
        this.seatsStr = seatsStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seat) {
        this.seats = seats;
    }

    public String getSeatsStr() {
        return seatsStr;
    }

    public void setSeatsStr(String seatsStr) {
        this.seatsStr = seatsStr;
    }
}