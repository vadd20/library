package ru.vp.library.domain;

import javax.persistence.Id;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.09
 */
public class Admin {

    @Id
    String id;

    String name;

    String login;

    String password;
}
