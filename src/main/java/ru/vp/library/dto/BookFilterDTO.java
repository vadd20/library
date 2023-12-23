package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.23
 */
@Getter
@Setter
public class BookFilterDTO {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String publishedDate;
    private String publisher;
    private Integer pageCount;
    private String location;
    private Integer price;

    public boolean isFilterSet() {
        return (genre != null && !genre.isEmpty())
            || (author != null && !author.isEmpty())
            || (title != null && !title.isEmpty())
            || (isbn != null && !isbn.isEmpty())
            || (publishedDate != null && !publishedDate.isEmpty())
            || (publisher != null && !publisher.isEmpty())
            || (pageCount != null)
            || (location != null && !location.isEmpty())
            || (price != null);
    }
}
