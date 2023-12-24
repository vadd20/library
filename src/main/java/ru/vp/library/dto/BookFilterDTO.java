package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

    private String publisher;
    private Integer pageCount;
    private String location;
    private Integer price;

    public boolean isFilterSet() {
        return (genre != null && !genre.isEmpty())
            || (author != null && !author.isEmpty())
            || (title != null && !title.isEmpty())
            || (isbn != null && !isbn.isEmpty())
            || (publishedDate != null)
            || (publisher != null && !publisher.isEmpty())
            || (pageCount != null)
            || (location != null && !location.isEmpty())
            || (price != null);
    }
}
