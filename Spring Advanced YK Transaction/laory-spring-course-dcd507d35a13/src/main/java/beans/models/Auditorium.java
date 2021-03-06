package beans.models;

import util.CsvUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = { "id", "name", "seatsNumber", "vipSeats" }, name = "auditorium")
@XmlRootElement
public class Auditorium {

    @XmlElement(required = true)
    private long   id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private int    seatsNumber;
    @XmlElement(required = true)
    private String vipSeats;

    public Auditorium() {
    }

    public Auditorium(String name, int seatsNumber, List<Integer> vipSeats) {
        this(-1, name, seatsNumber, vipSeats);
    }

    public Auditorium(long id, String name, int seatsNumber, List<Integer> vipSeats) {
        this(id, name, seatsNumber, CsvUtil.fromListToCsv(vipSeats));
    }

    public Auditorium(long id, String name, int seatsNumber, String vipSeats) {
        this.id = id;
        this.name = name;
        this.seatsNumber = seatsNumber;
        this.vipSeats = vipSeats;
    }

    public Auditorium withId(Long id) {
        return new Auditorium(id, name, seatsNumber, vipSeats);
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
    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @XmlTransient
    public String getVipSeats() {
        return vipSeats;
    }

    @XmlTransient
    public List<Integer> getVipSeatsList() {
        return CsvUtil.fromCsvToList(vipSeats, Integer:: valueOf);
    }

    public void setVipSeatsList(List<Integer> vipSeats) {
        this.vipSeats = CsvUtil.fromListToCsv(vipSeats);
    }

    public void setVipSeats(String vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Auditorium))
            return false;

        Auditorium that = (Auditorium) o;

        if (id != that.id)
            return false;
        if (seatsNumber != that.seatsNumber)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return vipSeats != null ? vipSeats.equals(that.vipSeats) : that.vipSeats == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + seatsNumber;
        result = 31 * result + (vipSeats != null ? vipSeats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", seatsNumber=" + seatsNumber +
               ", vipSeats=" + vipSeats +
               '}';
    }
}
