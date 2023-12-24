package ru.vp.library.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.Book;
import ru.vp.library.dto.BookDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookRepository extends JpaRepository<Book, String> {

    Boolean existsByIsbn(String isbn);

    String deleteBookByIsbn(String isbn);

    Book findByIsbn(String isbn);

    Optional<Book> findById(String id);

    @Query(
        "SELECT new ru.vp.library.dto.BookDTO(b.id, b.isbn, b.title, b.author, b.genre, b.publishedDate, b.publisher, " +
            "b.pageCount, b.location, b.price, COUNT(bi.id), SUM(CASE WHEN bi.isAvailable = TRUE THEN 1 ELSE 0 END)) FROM Book b " +
            "LEFT JOIN BookInstance bi ON b.id = bi.bookId " +
            "WHERE (:isbn IS NULL OR b.isbn LIKE CONCAT('%', :isbn, '%')) AND " +
            "(:title IS NULL OR b.title LIKE CONCAT('%', :title, '%')) AND " +
            "(:author IS NULL OR b.author LIKE CONCAT('%', :author, '%')) AND " +
            "(:genre IS NULL OR b.genre LIKE CONCAT('%', :genre, '%')) AND " +
            "(:publisher IS NULL OR b.publisher LIKE CONCAT('%', :publisher, '%')) AND " +
            "(:pageCount IS NULL OR b.pageCount = :pageCount) AND " +
            "(:price IS NULL OR b.price = :price) " +
            "GROUP BY b.id")
    List<BookDTO> findBooksByFilter(@Param("isbn") String isbn,
                                    @Param("title") String title,
                                    @Param("author") String author,
                                    @Param("genre") String genre,
                                    @Param("publisher") String publisher,
                                    @Param("pageCount") Integer pageCount,
                                    @Param("price") Integer price);

    @Query(
        "SELECT new ru.vp.library.dto.BookDTO(b.id, b.isbn, b.title, b.author, b.genre, b.publishedDate, b.publisher, " +
            "b.pageCount, b.location, b.price, COUNT(bi.id), SUM(CASE WHEN bi.isAvailable = TRUE THEN 1 ELSE 0 END)) FROM Book b " +
            "LEFT JOIN BookInstance bi ON b.id = bi.bookId " +
            "GROUP BY b.id")
    List<BookDTO> findAllBooks();
}
