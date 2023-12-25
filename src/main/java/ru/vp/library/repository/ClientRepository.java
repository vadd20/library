package ru.vp.library.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.Client;
import ru.vp.library.dto.ClientDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface ClientRepository extends JpaRepository<Client, String> {

    Boolean existsByReaderTicketNumber(String readerTicketNumber);

    Optional<Client> findByReaderTicketNumber(String readerTicketNumber);

    @Query(value =
        "SELECT new ru.vp.library.dto.ClientDTO(c.name, c.readerTicketNumber, c.dateOfBirth, c.email, c.phoneNumber, c.registrationDate) FROM Client c " +
            "WHERE (:name IS NULL OR c.name LIKE CONCAT('%', :name, '%')) and " +
            "(:readerTicketNumber IS NULL OR c.readerTicketNumber LIKE CONCAT('%', :readerTicketNumber, '%')) ")
    List<ClientDTO> findClientsByFilter(@Param("name") String name,
                                        @Param("readerTicketNumber") String readerTicketNumber);

    @Query(value =
        "SELECT new ru.vp.library.dto.ClientDTO(c.name, c.readerTicketNumber, c.dateOfBirth, c.email, c.phoneNumber, c.registrationDate) FROM Client c")
    List<ClientDTO> findAllClients();
}
