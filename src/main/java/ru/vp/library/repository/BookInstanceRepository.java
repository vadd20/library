package ru.vp.library.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vp.library.domain.BookInstance;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
public interface BookInstanceRepository extends JpaRepository<BookInstance, String> {

    @Query(value = "SELECT MAX(CAST(SPLIT_PART(id, '_', 2) AS INTEGER)) FROM book_instance WHERE id LIKE CONCAT(?1, '_%')", nativeQuery = true)
    Integer findMaxNumberByIsbn(String isbn);

    boolean existsById(String isbn);

    void deleteBookInstanceById(String isbn);

    void deleteAllByBookId(String bookId);

    Optional<BookInstance> findById(String isbn);

    List<BookInstance> findAllByBookId(String bookId);
}
