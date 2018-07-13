package beans.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlType(propOrder = { "id", "email", "name", "birthday", "password", "role" }, name = "user")
@XmlRootElement
public class User {


    @XmlElement(required = true)
    protected long id;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true, type = String.class)
    private LocalDate birthday;
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private String role;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public User() {
    }

    public User(long id, String email, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public User(long id, String email, String name, LocalDate birthday, String password, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.password = password;
        this.role = role;
    }

   public User(long id, String email, String name, String birthday, String password, String role) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateBirthday = LocalDate.parse(birthday, formatter);

        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = localDateBirthday;
        this.password = password;
        this.role = role;
    }

    public User(String email, String name, LocalDate birthday) {
        this(-1, email, name, birthday);
    }

    public User(String email, String name, LocalDate birthday, String password, String role) {
        this(-1, email, name, birthday, password, role);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday);
    }
    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlTransient
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /*public void setBirthday(String birthday) {

        LocalDate localDateBirthday = LocalDate.parse(birthday, formatter);
        this.birthday = localDateBirthday;

    }*/

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
