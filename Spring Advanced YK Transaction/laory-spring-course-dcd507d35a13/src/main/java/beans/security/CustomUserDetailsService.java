package beans.security;

import beans.models.User;
import beans.security.models.UserSecurity;
import beans.security.models.UserSecurityImpl;
import beans.security.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import beans.services.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        User userTest = userService.getUserByEmail(userName);
        System.out.println("!!!!!!!!!!!!!!UserTest: " + userTest.toString());

        //User user = userSecurityService.getByEmail(userName);

        UserSecurity user = userSecurityService.getByEmail(userName);
        System.out.println("!!!!!!!!!!!!!!UserSecurity: " + user.toString());

        if (user == null) {
            throw new UsernameNotFoundException("User with login " + userName + " not found");
        }
        UserSecurityImpl userSecurityDetails = new UserSecurityImpl(user);

        return userSecurityDetails;


    }
}