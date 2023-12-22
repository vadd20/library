package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.Book;
import ru.vp.library.dto.BookDTO;
import ru.vp.library.repository.BookRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookDTO bookDTO) {
        Book book = new Book();
        //BeanUtils.copyProperties(bookDTO, book);
        book.setId(bookDTO.getId());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPublishedDate(
            LocalDate.parse(bookDTO.getPublishedDate()));
        book.setPublisher(bookDTO.getPublisher());
        book.setPageCount(bookDTO.getPageCount());
        book.setLocation(bookDTO.getLocation());
        book.setPrice(bookDTO.getPrice());
        book.setTotalNumber(bookDTO.getTotalNumber());
        book.setIsDeleted(false);
        return bookRepository.save(book);
    }

    public Boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }

    public Book findByIbsn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(String id) {
        return bookRepository.findById(id);
    }

    public String deleteBookByIsbn(String isbn) {
        return bookRepository.deleteBookByIsbn(isbn);
    }

    // Дополнительные методы по необходимости
}

