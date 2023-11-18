package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления отзыва о книге.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */
@Entity
public class BookReview {

    @Id
    private String id;

    private String bookId;

    private String clientId;

    private LocalDate reviewDate;

    private String reviewText;

    private int rating;
}
