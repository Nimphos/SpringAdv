package beans.transaction.dao;

import beans.daos.UserDAO;
import beans.models.User;
import beans.transaction.models.UserAccount;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Repository(value = "userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserAccount get(long id) {
        return getCurrentSession().get(UserAccount.class, id);
    }

    @Override
    public UserAccount getByUser(User user) {
        //return ((UserAccount) createBlankCriteria(UserAccount.class).add(Restrictions.eq("user", user)).uniqueResult());
        Query query = getCurrentSession().createQuery("from UserAccount a where a.user = :user");
        query.setParameter("user", user);
        return ((UserAccount) query.list().get(0));
        //System.out.println("UserAccountDAOImpl 'getByUserId'");
        //return ((UserAccount) createBlankCriteria(UserAccount.class).add(Restrictions.eq("userId", userId)).uniqueResult());
    }

    @Override
    //@SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return ((List<UserAccount>) createBlankCriteria(UserAccount.class).list());
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        UserAccountDAO.validateUserAccount(userAccount);
        User user = userDAO.get(userAccount.getUser().getId());
        if (Objects.isNull(user)) {
            throw new IllegalStateException(
                    String.format("Unable to save user account: [%s]. User with id: [%s] does not exist.", userAccount,
                            userAccount.getUser().getId()));
        } else {
            Long userAccountId = (Long) getCurrentSession().save(userAccount);
            return this.get(userAccountId);
        }
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        UserAccountDAO.validateUserAccount(userAccount);
        User user = userDAO.get(userAccount.getUser().getId());
        UserAccount userAccountById = this.get(userAccount.getId());
        if (Objects.isNull(user)) {
            throw new IllegalStateException(
                    String.format("Unable to update user account: [%s]. User with id: [%s] does not exist.", userAccount,
                            userAccount.getUser().getId()));
        } else if (Objects.isNull(userAccountById)) {
            throw new IllegalStateException(
                    String.format("Unable to update user account: [%s]. User account with id: [%s] does not exist.", userAccount,
                            userAccount.getId()));
        } else {
            getCurrentSession().update(userAccount);
            return this.get(userAccount.getId());
        }
    }

    @Override
    public void delete(UserAccount userAccount) {
        UserAccountDAO.validateUserAccount(userAccount);
        getCurrentSession().delete(userAccount);
    }
}
