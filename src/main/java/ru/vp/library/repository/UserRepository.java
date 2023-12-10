package ru.vp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vp.library.domain.User;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */

public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);
}
