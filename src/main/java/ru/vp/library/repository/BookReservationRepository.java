package ru.vp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vp.library.domain.BookReservation;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookReservationRepository extends JpaRepository<BookReservation, String> {
}
