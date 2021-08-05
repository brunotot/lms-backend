package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tvz.btot.zavrsni.domain.User;

import java.io.Serial;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user == null) {
            return null;
        }
        return this.user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.user == null ? null : this.user.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return this.user == null ? null : this.user.getEmail();
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
        return this.user == null || this.user.getActive() == 1;
    }

}