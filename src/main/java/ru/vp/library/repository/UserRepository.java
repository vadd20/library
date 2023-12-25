package ru.vp.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.User;
import ru.vp.library.dto.UserDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */

public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);

    Boolean existsByLogin(String login);

    @Query(value =
        "SELECT new ru.vp.library.dto.UserDTO(u.name, u.login, u.password, u.role) FROM User u " +
            "WHERE (:name IS NULL OR u.name LIKE CONCAT('%', :name, '%')) and " +
            "(:login IS NULL OR u.login LIKE CONCAT('%', :login, '%')) and " +
            "(:role IS NULL OR u.role LIKE CONCAT('%', :role, '%')) ")
    List<UserDTO> findUsersByFilter(@Param("name") String name,
                                    @Param("login") String login,
                                    @Param("role") String role);

    @Query(value =
        "SELECT new ru.vp.library.dto.UserDTO(u.name, u.login, u.password, u.role) FROM User u ")
    List<UserDTO> findAllUsers();
}
