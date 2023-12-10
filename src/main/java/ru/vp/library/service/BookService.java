package ru.vp.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        book.setId(UUID.randomUUID().toString());
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPublishedDate(
            LocalDate.parse(bookDTO.getPublishedDate()));
        book.setPublisher(bookDTO.getPublisher());
        book.setPageCount(
            Integer.parseInt(bookDTO.getPageCount()));
        book.setLocation(bookDTO.getLocation());
        book.setPrice(
            Integer.parseInt(bookDTO.getPrice()));
        book.setIsDeleted(false);
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(String id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    // Дополнительные методы по необходимости
}

