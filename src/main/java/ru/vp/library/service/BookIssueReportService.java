package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.BookIssueReport;
import ru.vp.library.repository.BookIssueReportRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class BookIssueReportService {

    private final BookIssueReportRepository bookIssueReportRepository;

    public BookIssueReportService(BookIssueReportRepository bookIssueReportRepository) {
        this.bookIssueReportRepository = bookIssueReportRepository;
    }

    public List<BookIssueReport> findAllBookIssueReports() {
        return bookIssueReportRepository.findAll();
    }

    public Optional<BookIssueReport> findBookIssueReportById(String id) {
        return bookIssueReportRepository.findById(id);
    }

    public BookIssueReport saveBookIssueReport(BookIssueReport bookIssueReport) {
        return bookIssueReportRepository.save(bookIssueReport);
    }

    public void deleteBookIssueReport(String id) {
        bookIssueReportRepository.deleteById(id);
    }
}
