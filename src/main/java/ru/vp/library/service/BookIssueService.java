package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookIssue;
import ru.vp.library.repository.BookIssueRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;

    public BookIssueService(BookIssueRepository bookIssueRepository) {
        this.bookIssueRepository = bookIssueRepository;
    }

    public List<BookIssue> findAllBookIssues() {
        return bookIssueRepository.findAll();
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
