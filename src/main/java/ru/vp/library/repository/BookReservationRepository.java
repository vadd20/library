package ru.vp.library.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.BookReservation;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.dto.ReservationDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookReservationRepository extends JpaRepository<BookReservation, String> {

    boolean existsByBookInstanceIdAndClientId(String isbn, String clientId);

    Optional<BookReservation> findByBookInstanceId(String isbn);

    @Query(value =
        "SELECT new ru.vp.library.dto.ReservationDTO(r.bookInstanceId, c.readerTicketNumber, r.reservationDate, r.expirationDate, r.status) FROM BookReservation r " +
            "LEFT JOIN Client c ON c.id = r.clientId " +
            "WHERE (cast(:expirationDateFrom as date) is null or r.expirationDate >= :expirationDateFrom) and " +
            "(cast(:expirationDateTo as date) is null or r.expirationDate <= :expirationDateTo) and " +
            "(:status IS NULL OR r.status LIKE CONCAT('%', :status, '%')) ")
    List<ReservationDTO> findBooksByFilter(@Param("expirationDateFrom") LocalDate expirationDateFrom,
                                           @Param("expirationDateTo") LocalDate expirationDateTo,
                                           @Param("status") String status);

    @Query(
        "SELECT new ru.vp.library.dto.ReservationDTO(r.bookInstanceId, c.readerTicketNumber, r.reservationDate, r.expirationDate, r.status) FROM BookReservation r " +
            "LEFT JOIN Client c ON c.id = r.clientId ")
    List<ReservationDTO> findAllBookReservations();

    @Modifying
    @Query("update BookReservation r set r.status = 'Недействительно'" +
        " where r.expirationDate < current_date and r.status = 'Активно'")
    void updateExpiredReservationStatus();
}
