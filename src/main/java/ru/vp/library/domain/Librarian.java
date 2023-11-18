package ru.vp.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления библиотекаря.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
public class Librarian {

    @Id
    String id;

    String name;
}
