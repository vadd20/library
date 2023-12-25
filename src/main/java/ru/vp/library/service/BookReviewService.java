package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookReview;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.dto.IssueFilterDTO;
import ru.vp.library.dto.ReviewDTO;
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
    private final BookService bookService;
    private final ClientService clientService;
    
    public BookReviewService(BookReviewRepository bookReviewRepository,
                             BookService bookService, ClientService clientService,
                             ClientService clientService1) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookService = bookService;
        this.clientService = clientService1;
    }

    public boolean existsByBookIdAndClientId(String bookId, String clientId) {
        return bookReviewRepository.existsByBookIdAndClientId(bookId, clientId);
    }

    public BookReview findByBookIdAndClientId(String bookId, String clientId) {
        return bookReviewRepository.findByBookIdAndClientId(bookId, clientId);
    }


    public BookReview createBookReview(ReviewDTO reviewDTO) {
        BookReview bookReview = new BookReview();
        //BeanUtils.copyProperties(bookDTO, book);
        bookReview.setId(UUID.randomUUID().toString());
        bookReview.setBookId(bookService.findByIbsn(reviewDTO.getIsbn()).getId());
        bookReview.setClientId(clientService.findByReaderTicketNumber(reviewDTO.getReaderTicketNumber()).get().getId());
        bookReview.setReviewDate(reviewDTO.getReviewDate());
        bookReview.setReviewText(reviewDTO.getReviewText());
        bookReview.setRating(reviewDTO.getRating());
        return bookReviewRepository.save(bookReview);
    }

    public void deleteBookReview(String id) {
        bookReviewRepository.deleteById(id);
    }

    public List<ReviewDTO> findReviewsByFilter(ReviewDTO filter) {
        return bookReviewRepository.findReviewsByFilter(
            filter.getIsbn(),
            filter.getReaderTicketNumber(),
            filter.getRating()
        );
    }

    public List<ReviewDTO> findAllReviews() {
        return bookReviewRepository.findAllReviews();
    }
}
