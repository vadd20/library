package ru.vp.library.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vp.library.domain.BookIssueReport;
import ru.vp.library.service.BookIssueReportService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */

@RestController
@RequestMapping("/booksIssueReport")
public class BookIssueReportController {

    private final BookIssueReportService bookIssueReportService;

    public BookIssueReportController(BookIssueReportService bookIssueReportService) {
        this.bookIssueReportService = bookIssueReportService;
    }

    @GetMapping
    public List<BookIssueReport> getAllBookIssueReports() {
        return bookIssueReportService.findAllBookIssueReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookIssueReport> getBookIssueReportById(@PathVariable String id) {
        return bookIssueReportService.findBookIssueReportById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookIssueReport createBookIssueReport(@RequestBody BookIssueReport bookIssueReport) {
        return bookIssueReportService.saveBookIssueReport(bookIssueReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookIssueReport(@PathVariable String id) {
        bookIssueReportService.deleteBookIssueReport(id);
        return ResponseEntity.ok().build();
    }
}
