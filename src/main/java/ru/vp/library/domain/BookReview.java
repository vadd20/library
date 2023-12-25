package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Модель представления отзыва о книге.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */
@Entity
@Getter
@Setter
public class BookReview {

    @Id
    private String id;

    private String bookId;

    private String clientId;

    private LocalDate reviewDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String reviewText;

    private int rating;
}
