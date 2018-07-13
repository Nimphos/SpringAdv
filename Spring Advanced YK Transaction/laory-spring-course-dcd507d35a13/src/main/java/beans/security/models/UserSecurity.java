package beans.security.models;

import beans.models.User;

import java.time.LocalDate;
import java.util.Date;

public class UserSecurity extends User {
    /*private String password;
    private String role;*/

    public UserSecurity() {
    }

    public UserSecurity(long id, String name, LocalDate dateBirth, String email, String password, String role) {
        super(id, email, name, dateBirth, password, role);
        /*this.password = password;
        this.role = role;*/
    }

    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
