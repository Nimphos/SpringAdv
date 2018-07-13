package beans.services;

import beans.daos.BookingDAO;
import beans.daos.UserDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.security.models.UserSecurity;
import beans.security.models.UserSecurityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Autowired
    private ReflectionSaltSource saltSource;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(User user) {
        // autowire password encoder and put it into BD's password field
        UserSecurityImpl userSecurityImpl = new UserSecurityImpl(new UserSecurity(user.getId(), user.getName(), user.getBirthday(), user.getEmail(), user.getPassword(), user.getRole()));
        String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(userSecurityImpl));
        user.setPassword(encodedPassword);
        return userDAO.create(user);
    }

    public void remove(User user) {
        userDAO.delete(user);
    }

    public User getById(long id) {
        return userDAO.get(id);
    }

    public User getUserByEmail(String email) {
        System.out.println("UserServiceImpl 'getUserByEmail'");
        return userDAO.getByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    public List<User> getAll() {
        System.out.println("UserServiceImpl 'getAll'");
        return userDAO.getAll();
    }

    public List<Ticket> getBookedTickets(User user) {
        return bookingDAO.getTickets(user);
    }
}
