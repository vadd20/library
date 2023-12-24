package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.26B
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private String genre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;
    
    private String publisher;
    private int pageCount;
    private String location;
    private int price;
    private long totalNumber;
    private long totalAvailableNumber;
}
