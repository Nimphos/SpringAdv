package beans.transaction.service;

import beans.transaction.models.UserAccount;

import java.util.List;

public interface UserAccountService {
    public UserAccount getById(int id);
    public UserAccount getByUserEmail(String email);
    public List<UserAccount> getAll();
    public UserAccount create(UserAccount userAccount);
    public UserAccount update(UserAccount userAccount);
    void remove(UserAccount userAccount);
}
