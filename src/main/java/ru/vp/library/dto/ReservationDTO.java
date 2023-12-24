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
 * @since 2023.12.24
 */
@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private String id;
    private String isbn;
    private String readerTicketNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private String status;

    public ReservationDTO(String isbn, String readerTicketNumber, LocalDate reservationDate,
                          LocalDate expirationDate, String status) {
        this.isbn = isbn;
        this.readerTicketNumber = readerTicketNumber;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }
}
