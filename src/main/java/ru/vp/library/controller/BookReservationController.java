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
import ru.vp.library.domain.BookReservation;
import ru.vp.library.service.BookReservationService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */
@RestController
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @GetMapping
    public List<BookReservation> getAllBookReservations() {
        return bookReservationService.findAllBookReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReservation> getBookReservationById(@PathVariable String id) {
        return bookReservationService.findBookReservationById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookReservation createBookReservation(@RequestBody BookReservation bookReservation) {
        return bookReservationService.saveBookReservation(bookReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookReservation(@PathVariable String id) {
        bookReservationService.deleteBookReservation(id);
        return ResponseEntity.ok().build();
    }
}
