package ru.vp.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель пользователя.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
@Getter
@Setter
@Table(name="user", schema="public")
public class User {

    @Id
    String id;

    String name;

    String login;

    String password;

    String role;
}
