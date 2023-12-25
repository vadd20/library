package ru.vp.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.BookReview;
import ru.vp.library.dto.BookDTO;
import ru.vp.library.dto.ReviewDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookReviewRepository extends JpaRepository<BookReview, String> {

    boolean existsByBookIdAndClientId(String bookId, String clientId);

    BookReview findByBookIdAndClientId(String bookId, String clientId);

    @Query(
        "SELECT new ru.vp.library.dto.ReviewDTO(b.isbn, c.readerTicketNumber, br.reviewDate, br.reviewText, br.rating) " +
            "FROM BookReview br " +
            "LEFT JOIN Book b ON b.id = br.bookId " +
            "LEFT JOIN Client c on c.id = br.clientId " +
            "WHERE (:isbn IS NULL OR b.isbn LIKE CONCAT('%', :isbn, '%')) AND " +
            "(:readerTicketNumber IS NULL OR c.readerTicketNumber LIKE CONCAT('%', :readerTicketNumber, '%')) AND " +
            "(:rating IS NULL OR br.rating = :rating)")
    List<ReviewDTO> findReviewsByFilter(@Param("isbn") String isbn,
                                        @Param("readerTicketNumber") String readerTicketNumber,
                                        @Param("rating") Integer rating);

    @Query(
        "SELECT new ru.vp.library.dto.ReviewDTO(b.isbn, c.readerTicketNumber, br.reviewDate, br.reviewText, br.rating) " +
            "FROM BookReview br " +
            "LEFT JOIN Book b ON b.id = br.bookId " +
            "LEFT JOIN Client c on c.id = br.clientId")
    List<ReviewDTO> findAllReviews();
}
