package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления книги.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
public class Book {

    @Id
    String id;

    String isbn;

    String title;

    String author;

    String genre;

    LocalDate publishedDate;

    String publisher;

    Integer pageCount;

    String location;

    Boolean isAvailable;

    Integer price;

    public void setId(String id) {
        this.id = id;
    }
}
