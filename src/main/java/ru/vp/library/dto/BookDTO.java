package ru.vp.library.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.26B
 */
@Getter
@Setter
public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String publishedDate;
    private String publisher;
    private String pageCount;
    private String location;
    private String isAvailable;
    private String price;
}
