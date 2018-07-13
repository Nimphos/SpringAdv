package beans.transaction.dao;

import beans.models.User;
import beans.transaction.models.UserAccount;

import java.util.List;
import java.util.Objects;

public interface UserAccountDAO {

    UserAccount get(long id);
    UserAccount getByUser(User user);
    List<UserAccount> getAll();
    UserAccount create(UserAccount userAccount);
    UserAccount update(UserAccount userAccount);
    void delete(UserAccount userAccount);

    static void validateUserAccount(UserAccount userAccount) {
        if (Objects.isNull(userAccount)) {
            throw new NullPointerException("UserAccount is [null]");
        }
        if (Objects.isNull(userAccount.getUser().getId())) {
            throw new NullPointerException("UserAccount's userId is [null]. UserAccount: [" + userAccount + "]");
        }
        if (Objects.isNull(userAccount.getDeposit())) {
            throw new NullPointerException("UserAccount's deposit is [null]. UserAccount: [" + userAccount + "]");
        }
    }
}
