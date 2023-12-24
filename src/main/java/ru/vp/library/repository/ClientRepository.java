package ru.vp.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vp.library.domain.Client;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface ClientRepository extends JpaRepository<Client, String> {

    Boolean existsByReaderTicketNumber(String readerTicketNumber);

    Optional<Client> findByReaderTicketNumber(String readerTicketNumber);
}
