package ru.vp.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления отчета о выдачи книги.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */
@Entity
public class BookIssueReport {

    @Id
    private String id;

    private String bookId;

    private int issueCount;

    private int month;

    private int year;
}
