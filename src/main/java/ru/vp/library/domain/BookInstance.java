package ru.vp.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность экземпляра книги. Идентификатором является строка "isbn_порядковый номер".
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
@Entity
@Getter
@Setter
public class BookInstance {

    @Id
    String id;

    String bookId;

    Boolean isAvailable;
}
