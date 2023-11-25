package ru.vp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vp.library.domain.Librarian;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface LibrarianRepository extends JpaRepository<Librarian, String> {
}
