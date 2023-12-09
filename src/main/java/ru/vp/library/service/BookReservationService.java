package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookReservation;
import ru.vp.library.repository.BookReservationRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookReservationService {

    private final BookReservationRepository bookReservationRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    public List<BookReservation> findAllBookReservations() {
        return bookReservationRepository.findAll();
    }

    public Optional<BookReservation> findBookReservationById(String id) {
        return bookReservationRepository.findById(id);
    }

    public BookReservation saveBookReservation(BookReservation bookReservation) {
        return bookReservationRepository.save(bookReservation);
    }

    public void deleteBookReservation(String id) {
        bookReservationRepository.deleteById(id);
    }
}
