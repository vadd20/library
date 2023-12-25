package ru.vp.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String name;

    String login;

    String password;

    String role;

    public boolean isFilterSet() {
        return (name != null && !name.isEmpty())
            || (login != null && !login.isEmpty())
            || (role != null && !role.isEmpty());
    }
}
