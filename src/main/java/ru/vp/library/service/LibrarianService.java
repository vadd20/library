package ru.vp.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.Librarian;
import ru.vp.library.repository.LibrarianRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
@Service
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public List<Librarian> findAllLibrarians() {
        return librarianRepository.findAll();
    }

    public Optional<Librarian> findLibrarianById(String id) {
        return librarianRepository.findById(id);
    }

    public Librarian saveLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    public void deleteLibrarian(String id) {
        librarianRepository.deleteById(id);
    }
}
