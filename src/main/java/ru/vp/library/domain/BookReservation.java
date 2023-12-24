package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Модель представления записи в журнале бронирования.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
@Setter
@Getter
public class BookReservation {

    @Id
    String id;

    String bookInstanceId;

    String clientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate reservationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate expirationDate;

    String status;
}
