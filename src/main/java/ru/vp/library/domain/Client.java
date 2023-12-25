package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель представления клиента.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    String id;

    String name;

    LocalDate dateOfBirth;

    String email;

    String phoneNumber;

    String readerTicketNumber;

    LocalDate registrationDate;
}
