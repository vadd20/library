package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.25
 */
@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private String isbn;
    private String readerTicketNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reviewDate;

    private String reviewText;
    private Integer rating;

    public boolean isFilterSet() {
        return (isbn != null && !isbn.isEmpty())
            || (readerTicketNumber != null && !readerTicketNumber.isEmpty())
            || (rating != null);
    }

    public ReviewDTO(String isbn, String readerTicketNumber, LocalDate reviewDate, String reviewText,
                     Integer rating) {
        this.isbn = isbn;
        this.readerTicketNumber = readerTicketNumber;
        this.reviewDate = reviewDate;
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
