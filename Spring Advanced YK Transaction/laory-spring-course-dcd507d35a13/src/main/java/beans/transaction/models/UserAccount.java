package beans.transaction.models;

import beans.models.User;

public class UserAccount {

    protected long id;
    private User user;
    private Double deposit;

    public UserAccount(){}

    public UserAccount(long id, User user, Double deposit) {
            this.id = id;
        this.user = user;
        this.deposit = deposit;
    }

    public UserAccount(User user, Double deposit) {
        this(-1, user, deposit);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return deposit != null ? deposit.equals(that.deposit) : that.deposit == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user +
                ", deposit=" + deposit +
                '}';
    }
}

