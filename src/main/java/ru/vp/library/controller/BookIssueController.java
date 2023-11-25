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
import ru.vp.library.domain.BookIssue;
import ru.vp.library.service.BookIssueService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */
@RestController
@RequestMapping("/booksIssue")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @GetMapping
    public List<BookIssue> getAllBookIssues() {
        return bookIssueService.findAllBookIssues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookIssue> getBookIssueById(@PathVariable String id) {
        return bookIssueService.findBookIssueById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookIssue createBookIssue(@RequestBody BookIssue bookIssue) {
        return bookIssueService.saveBookIssue(bookIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookIssue(@PathVariable String id) {
        bookIssueService.deleteBookIssue(id);
        return ResponseEntity.ok().build();
    }
}
