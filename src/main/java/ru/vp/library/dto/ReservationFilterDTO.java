package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
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
public class ReservationFilterDTO {

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateTo;

    public boolean isFilterSet() {
        return (expirationDateFrom != null)
            || (expirationDateTo != null) || (status != null);
    }
}
