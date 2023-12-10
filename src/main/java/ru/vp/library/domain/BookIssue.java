package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления записи о выдачи книг.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
public class BookIssue {

    @Id
    String id;

    String bookId;

    String clientId;

    String librarianId;

    LocalDate issueDate;

    LocalDate dueDate;

    LocalDate returnDate;

    Boolean isOverdue;


}
