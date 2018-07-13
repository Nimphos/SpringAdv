package beans.transaction.service;

import beans.daos.UserDAO;
import beans.models.User;
import beans.transaction.dao.UserAccountDAO;
import beans.transaction.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserAccount getById(int id) {
        return userAccountDAO.get(id);
    }

    @Override
    public UserAccount getByUserEmail(String email) {
        User user = userDAO.getByEmail(email);
        return userAccountDAO.getByUser(user);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountDAO.getAll();
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return userAccountDAO.create(userAccount);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return userAccountDAO.update(userAccount);
    }

    @Override
    public void remove(UserAccount userAccount) {
        userAccountDAO.delete(userAccount);
    }
}
