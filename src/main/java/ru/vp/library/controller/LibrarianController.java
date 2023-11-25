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
import ru.vp.library.domain.Librarian;
import ru.vp.library.service.LibrarianService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.findAllLibrarians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable String id) {
        return librarianService.findLibrarianById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Librarian createLibrarian(@RequestBody Librarian librarian) {
        return librarianService.saveLibrarian(librarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable String id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.ok().build();
    }
}