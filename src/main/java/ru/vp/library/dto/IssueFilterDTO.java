package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
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
public class IssueFilterDTO {

    private Boolean isOverdue;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateTo;


    public boolean isFilterSet() {
        return (dueDateFrom != null)
            || (dueDateTo != null) || (isOverdue != null);
    }
}
