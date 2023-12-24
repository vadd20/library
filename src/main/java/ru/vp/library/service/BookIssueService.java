package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookIssue;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.repository.BookIssueRepository;
import ru.vp.library.repository.ClientRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final ClientRepository clientRepository;

    public BookIssueService(BookIssueRepository bookIssueRepository,
                            ClientRepository clientRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.clientRepository = clientRepository;
    }

    public List<BookIssue> findAllBookIssues() {
        return bookIssueRepository.findAll();
    }

    public BookIssue createBookIssue(IssueDTO issueDTO) {
        BookIssue issue = new BookIssue();
        //BeanUtils.copyProperties(IssueDTO, issue);
        issue.setId(issueDTO.getId());
        issue.setBookInstanceId(issueDTO.getIsbn());
        issue.setClientId(clientRepository.findByReaderTicketNumber(issueDTO.getReaderTicketNumber()).get().getId());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(LocalDate.now().plusWeeks(2));
        issue.setLibrarianId("1");
        issue.setIsOverdue(false);
        return bookIssueRepository.save(issue);
    }

    public Optional<BookIssue> findBookIssueById(String id) {
        return bookIssueRepository.findById(id);
    }

    public BookIssue saveBookIssue(BookIssue bookIssue) {
        return bookIssueRepository.save(bookIssue);
    }

    public void deleteBookIssue(String id) {
        bookIssueRepository.deleteById(id);
    }
}
