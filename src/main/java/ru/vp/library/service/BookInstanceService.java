package ru.vp.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.vp.library.domain.Book;
import ru.vp.library.domain.BookInstance;
import ru.vp.library.repository.BookInstanceRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
@Service
public class BookInstanceService {

    private final BookInstanceRepository bookInstanceRepository;

    public BookInstanceService(BookInstanceRepository bookInstanceRepository) {
        this.bookInstanceRepository = bookInstanceRepository;
    }

    /**
     * @param book              книга
     * @param numberOfInstances количество экземпляров
     * @return возвращает номера созданных экземпляров
     */
    public List<Integer> createBookInstances(Book book, long numberOfInstances) {
        List<Integer> instanceNumbers = new ArrayList<>();
        int maxNumber = getMaxNumberForIsbn(book.getIsbn());
        for (int i = maxNumber + 1; i < maxNumber + numberOfInstances + 1; i++) {
            BookInstance bookInstance = new BookInstance();
            bookInstance.setId(book.getIsbn() + "_" + i);
            bookInstance.setBookId(book.getId());
            bookInstance.setIsAvailable(true);
            bookInstanceRepository.save(bookInstance);
            instanceNumbers.add(i);
        }
        return instanceNumbers;
    }

    /**
     * @param isbn номер isbn
     * @return возвращает последний порядковый номер экземпляра
     */
    private Integer getMaxNumberForIsbn(String isbn) {
        Integer maxNumber = bookInstanceRepository.findMaxNumberByIsbn(isbn);
        return (maxNumber != null) ? maxNumber : 0;
    }

    public List<BookInstance> deleteAllByBookId(String bookId) {
        return bookInstanceRepository.deleteAllByBookId(bookId);
    }

    public boolean existsById(String isbn) {
        return bookInstanceRepository.existsById(isbn);
    }

    public String deleteBookInstanceById(String isbn) {
        return bookInstanceRepository.deleteBookInstanceById(isbn);
    }

    public Optional<BookInstance> findByIsbn(String isbn) {
        return bookInstanceRepository.findById(isbn);
    }

    public void save(BookInstance bookInstance) {
        bookInstanceRepository.save(bookInstance);
    }
}
