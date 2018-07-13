package beans.security.services;

import beans.security.models.UserSecurity;
import beans.security.models.UserSecurityImpl;

public interface UserSecurityService {
    public UserSecurity getByEmail(String email);
    public boolean createUser(UserSecurityImpl user);
}
