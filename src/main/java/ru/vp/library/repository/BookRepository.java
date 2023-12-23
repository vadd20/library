package ru.vp.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.Book;

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

    @Query(value = "SELECT b FROM Book b WHERE " +
        "(:isbn IS NULL OR b.isbn LIKE :isbn) AND " +
        "(:title IS NULL OR b.title LIKE :title) AND " +
        "(:author IS NULL OR b.author LIKE :author) AND " +
        "(:genre IS NULL OR b.genre LIKE :genre) AND " +
        "(:publisher IS NULL OR b.publisher LIKE :publisher) AND " +
        "(:pageCount IS NULL OR b.page_count = :pageCount) AND " +
        "(:price IS NULL OR b.price = :price)", nativeQuery = true)
    List<Book> findBooksByFilter(@Param("isbn") String isbn,
                                 @Param("title") String title,
                                 @Param("author") String author,
                                 @Param("genre") String genre,
                                 @Param("publisher") String publisher,
                                 @Param("pageCount") Integer pageCount,
                                 @Param("price") Integer price);
}
