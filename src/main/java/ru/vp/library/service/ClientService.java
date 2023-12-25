package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.Client;
import ru.vp.library.dto.ClientDTO;
import ru.vp.library.dto.ReservationDTO;
import ru.vp.library.dto.ReservationFilterDTO;
import ru.vp.library.repository.BookIssueRepository;
import ru.vp.library.repository.BookReservationRepository;
import ru.vp.library.repository.ClientRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final BookReservationRepository bookReservationRepository;
    private final BookIssueRepository bookIssueRepository;


    public ClientService(ClientRepository clientRepository,
                         BookReservationRepository bookReservationRepository,
                         BookIssueRepository bookIssueRepository) {
        this.clientRepository = clientRepository;
        this.bookReservationRepository = bookReservationRepository;
        this.bookIssueRepository = bookIssueRepository;
    }

    public Boolean existsByReaderTicketNumber(String readerTicketNumber) {
        return clientRepository.existsByReaderTicketNumber(readerTicketNumber);
    }

    public Optional<Client> findByReaderTicketNumber(String readerTicketNumber) {
        return clientRepository.findByReaderTicketNumber(readerTicketNumber);
    }

    public Optional<Client> findClientById(String id) {
        return clientRepository.findById(id);
    }

    public void deleteById(String id) {
        bookIssueRepository.deleteAllByClientId(id);
        bookReservationRepository.deleteAllByClientId(id);
        clientRepository.deleteById(id);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client();
        //BeanUtils.copyProperties(bookDTO, book);
        client.setId(UUID.randomUUID().toString());
        client.setName(clientDTO.getName());
        client.setDateOfBirth(clientDTO.getDateOfBirth());
        client.setEmail(clientDTO.getEmail());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setReaderTicketNumber(clientDTO.getReaderTicketNumber());
        client.setRegistrationDate(LocalDate.now());
        return clientRepository.save(client);
    }

    public List<ClientDTO> findClientsByFilter(ClientDTO filter) {
        return clientRepository.findClientsByFilter(
            filter.getName(),
            filter.getReaderTicketNumber()
        );
    }

    public List<ClientDTO> findAllClients() {
        return clientRepository.findAllClients();
    }
}
