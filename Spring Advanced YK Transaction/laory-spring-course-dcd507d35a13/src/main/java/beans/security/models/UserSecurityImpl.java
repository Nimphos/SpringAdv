package beans.security.models;

import java.time.LocalDate;
import java.util.*;

import beans.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityImpl extends UserSecurity implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    public UserSecurityImpl(UserSecurity user) {
        super(user.getId(), user.getName(), user.getBirthday(), user.getEmail(), user.getPassword(), user.getRole());
    }

    /*public UserSecurityImpl(String name, String email, LocalDate birthday, String role, String password) {
        super(-1, name, birthday, email, password, role);
    }*/

    public long getId() {
        return id;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        String[] roles = this.getRole().split(",");
        List<String> userRoles = Arrays.asList(roles);
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (userRoles != null) {
            for(String role : roles)
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
