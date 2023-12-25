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
public class ClientDTO {
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String email;
    private String phoneNumber;
    private String readerTicketNumber;
    private LocalDate registrationDate;

    public boolean isFilterSet() {
        return (name != null && !name.isEmpty())
            || (readerTicketNumber != null && !readerTicketNumber.isEmpty());
    }

    public ClientDTO(String name, String readerTicketNumber, LocalDate dateOfBirth, String email, String phoneNumber,
                      LocalDate registrationDate) {
        this.name = name;
        this.readerTicketNumber = readerTicketNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }
}
