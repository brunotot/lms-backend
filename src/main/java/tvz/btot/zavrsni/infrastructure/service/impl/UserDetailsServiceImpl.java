package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return Optional
                .of(this.userRepository.findByUsername(username))
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));
    }

}
