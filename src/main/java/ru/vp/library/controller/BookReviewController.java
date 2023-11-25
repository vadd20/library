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
import ru.vp.library.domain.BookReview;
import ru.vp.library.service.BookReviewService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */

@RestController
@RequestMapping("/bookReview")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @GetMapping
    public List<BookReview> getAllBookReviews() {
        return bookReviewService.findAllBookReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReview> getBookReviewById(@PathVariable String id) {
        return bookReviewService.findBookReviewById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookReview createBookReview(@RequestBody BookReview bookReview) {
        return bookReviewService.saveBookReview(bookReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookReview(@PathVariable String id) {
        bookReviewService.deleteBookReview(id);
        return ResponseEntity.ok().build();
    }
}
