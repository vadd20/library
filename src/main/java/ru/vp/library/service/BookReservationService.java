package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookReservation;
import ru.vp.library.domain.ReservationStatus;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.dto.IssueFilterDTO;
import ru.vp.library.dto.ReservationDTO;
import ru.vp.library.dto.ReservationFilterDTO;
import ru.vp.library.repository.BookIssueRepository;
import ru.vp.library.repository.BookReservationRepository;
import ru.vp.library.repository.ClientRepository;
import ru.vp.library.service.user.AuthService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookReservationService {

    private final BookReservationRepository bookReservationRepository;
    private final BookIssueRepository bookIssueRepository;
    private final ClientRepository clientRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository,
                                  BookIssueRepository bookIssueRepository,
                                  ClientRepository clientRepository) {
        this.bookReservationRepository = bookReservationRepository;
        this.bookIssueRepository = bookIssueRepository;
        this.clientRepository = clientRepository;
    }

    public Optional<BookReservation> findByBookInstanceId(String isbn) {
        return bookReservationRepository.findByBookInstanceId(isbn);
    }

    public BookReservation save(BookReservation bookReservation) {
        return bookReservationRepository.save(bookReservation);
    }

    public BookReservation createBookReservation(ReservationDTO reservationDTO) {
        BookReservation reservation = new BookReservation();
        reservation.setId(reservationDTO.getId());
        reservation.setBookInstanceId(reservationDTO.getIsbn());
        reservation.setClientId(clientRepository.findByReaderTicketNumber(reservationDTO.getReaderTicketNumber()).get().getId());
        reservation.setReservationDate(LocalDate.now());
        reservation.setExpirationDate(LocalDate.now().plusWeeks(1));
        reservation.setStatus(ReservationStatus.ACTIVE.toString());
        return bookReservationRepository.save(reservation);
    }

    public boolean existsByBookInstanceIdAndClientId(String isbn, String clientId) {
        return bookReservationRepository.existsByBookInstanceIdAndClientId(isbn, clientId);
    }

    public List<ReservationDTO> findReservationsByFilter(ReservationFilterDTO filter) {
        return bookReservationRepository.findBooksByFilter(
            filter.getExpirationDateFrom(),
            filter.getExpirationDateTo(),
            filter.getStatus()
        );
    }

    public List<ReservationDTO> findAllBookReservations() {
        return bookReservationRepository.findAllBookReservations();
    }
}
