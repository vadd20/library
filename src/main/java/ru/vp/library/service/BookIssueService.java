package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookIssue;
import ru.vp.library.dto.BookDTO;
import ru.vp.library.dto.BookFilterDTO;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.dto.IssueFilterDTO;
import ru.vp.library.repository.BookIssueRepository;
import ru.vp.library.repository.ClientRepository;
import ru.vp.library.service.user.AuthService;

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

    public List<IssueDTO> findAllBookIssues() {
        return bookIssueRepository.findAllBookIssues();
    }

    public BookIssue createBookIssue(IssueDTO issueDTO) {
        BookIssue issue = new BookIssue();
        //BeanUtils.copyProperties(IssueDTO, issue);
        issue.setId(issueDTO.getId());
        issue.setBookInstanceId(issueDTO.getIsbn());
        issue.setClientId(clientRepository.findByReaderTicketNumber(issueDTO.getReaderTicketNumber()).get().getId());
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusWeeks(2));
        issue.setLibrarianId(AuthService.getCurrentUserId());
        issue.setIsOverdue(false);
        return bookIssueRepository.save(issue);
    }

    public boolean existsByBookInstanceId(String isbn) {
        return bookIssueRepository.existsByBookInstanceId(isbn);
    }

    public Optional<BookIssue> findByBookInstanceId(String id) {
        return bookIssueRepository.findByBookInstanceId(id);
    }

    public BookIssue saveBookIssue(BookIssue bookIssue) {
        return bookIssueRepository.save(bookIssue);
    }

    public List<IssueDTO> findIssuesByFilter(IssueFilterDTO filter) {
        return bookIssueRepository.findBooksByFilter(
            filter.getDueDateFrom(),
            filter.getDueDateTo(),
            filter.getIsOverdue()
        );
    }
}
