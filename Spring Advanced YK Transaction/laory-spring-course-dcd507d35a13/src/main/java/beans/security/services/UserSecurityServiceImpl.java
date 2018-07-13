package beans.security.services;

import beans.daos.UserDAO;
import beans.models.User;
import beans.security.models.UserSecurity;
import beans.security.models.UserSecurityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserSecurityServiceImpl implements UserSecurityService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;


    @Override
    public UserSecurity getByEmail(String email) {
        System.out.println("UserSecurityServiceImpl 'getByEmail'");
        return userDAO.getUserSecurityByEmail(email);
    }

    @Override
    public boolean createUser(UserSecurityImpl user) {
        /*UserSecurityImpl userSecurityImpl = new UserSecurityImpl(new UserSecurity(user.getId(), user.getName(), user.getBirthday(), user.getEmail(), user.getPassword(), user.getRole()));
        String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(userSecurityImpl));
        user.setPassword(encodedPassword);*/
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));
        User userToSave = new User(user.getId(), user.getEmail(), user.getUsername(), user.getBirthday(), user.getPassword(), user.getRole());
        User createdUser = userDAO.create(userToSave);
        if(createdUser != null)
            return true;
        else
            return false;
    }

    org.springframework.security.core.userdetails.User buildUserFromUserEntity(UserSecurityImpl userEntity) {
        String username = userEntity.getName();
        String password = userEntity.getPassword();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        boolean enabled = true;

        Collection<GrantedAuthority> authorities = userEntity.getAuthorities();

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
}
