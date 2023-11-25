package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookReservation;
import ru.vp.library.domain.BookReview;
import ru.vp.library.repository.BookReviewRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookReviewService {
    
    private final BookReviewRepository bookReviewRepository;
    
    public BookReviewService(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public List<BookReview> findAllBookReviews() {
        return bookReviewRepository.findAll();
    }

    public Optional<BookReview> findBookReviewById(String id) {
        return bookReviewRepository.findById(id);
    }

    public BookReview saveBookReview(BookReview bookReview) {
        return bookReviewRepository.save(bookReview);
    }

    public void deleteBookReview(String id) {
        bookReviewRepository.deleteById(id);
    }
}
