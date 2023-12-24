package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель представления книги.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
@Getter
@Setter
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

    Integer price;

}









