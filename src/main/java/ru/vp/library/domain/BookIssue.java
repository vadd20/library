package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель представления записи о выдачи книг.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
@Getter
@Setter
public class BookIssue {

    @Id
    String id;

    String bookInstanceId;

    String clientId;

    String librarianId;

    LocalDate issueDate;

    LocalDate returnDate;

    Boolean isOverdue;

}
