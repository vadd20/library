package ru.vp.library.service;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.User;
import ru.vp.library.repository.UserRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class LibraryUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public LibraryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}
