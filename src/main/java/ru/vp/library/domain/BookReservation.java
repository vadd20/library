package ru.vp.library.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Модель представления записи в журнале бронирования.
 *
 * @author Vadim Podogov
 * @since 2023.11.18
 */

@Entity
public class BookReservation {

    @Id
    String id;

    String bookId;

    String clientId;

    LocalDate reservationDate;

    LocalDate expirationDate;
}
