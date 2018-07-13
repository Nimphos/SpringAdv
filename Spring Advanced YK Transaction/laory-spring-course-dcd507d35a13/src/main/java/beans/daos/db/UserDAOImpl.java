package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserDAO;
import beans.models.User;
import beans.security.models.UserSecurity;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Transactional
@Repository(value = "userDAO")
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public User create(User user) {
        UserDAO.validateUser(user);
        User byEmail = getByEmail(user.getEmail());
        if (Objects.nonNull(byEmail)) {
            throw new IllegalStateException(
                    String.format("Unable to store user: [%s]. User with email: [%s] is already created.", user,
                                  user.getEmail()));
        } else {
            Long userId = (Long) getCurrentSession().save(user);
            return user.withId(userId);
        }
    }

    @Override
    public void delete(User user) {
        UserDAO.validateUser(user);
        getCurrentSession().delete(user);
    }

    @Override
    public User get(long id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        System.out.println("UserDAOImpl 'getByEmail'");
        return ((User) createBlankCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult());
    }

    @Override
    public UserSecurity getUserSecurityByEmail(String email) {
        System.out.println("UserDAOImpl 'getUserSecurityByEmail'");
        User user = (User) createBlankCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        System.out.println("UserDAOImpl 'user.toString()' " + user.toString());
        return new UserSecurity(user.getId(), user.getName(), user.getBirthday(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllByName(String name) {
        return createBlankCriteria(User.class).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return ((List<User>) createBlankCriteria(User.class).list());
    }
}
