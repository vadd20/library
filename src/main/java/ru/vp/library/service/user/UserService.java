package ru.vp.library.service.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.User;
import ru.vp.library.dto.UserDTO;
import ru.vp.library.repository.UserRepository;
import ru.vp.library.security.WebSecurityConfig;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.25
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final WebSecurityConfig webSecurityConfig;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public List<UserDTO> findUsersByFilter(UserDTO filter) {
        return userRepository.findUsersByFilter(
            filter.getName(),
            filter.getLogin(),
            filter.getRole()
        );
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
