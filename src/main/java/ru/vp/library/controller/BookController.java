package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_ACTION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_CREATION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_DELETE_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_LIST_FORM;
import static ru.vp.library.constants.UrlNames.BOOK_URL;
import static ru.vp.library.constants.UrlNames.CREATE_URL;
import static ru.vp.library.constants.UrlNames.DELETE_URL;
import static ru.vp.library.constants.UrlNames.SHOW_URL;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.Book;
import ru.vp.library.dto.BookDTO;
import ru.vp.library.dto.BookFilterDTO;
import ru.vp.library.dto.DeleteBookOrInstanceForm;
import ru.vp.library.service.BookInstanceService;
import ru.vp.library.service.BookService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */

@Controller
@RequestMapping(BOOK_URL)
public class BookController {

    private final BookService bookService;
    private final BookInstanceService bookInstanceService;

    public BookController(BookService bookService,
                          BookInstanceService bookInstanceService) {
        this.bookService = bookService;
        this.bookInstanceService = bookInstanceService;
    }

    @GetMapping
    public String bookActions() {
        return BOOK_ACTION_FORM;
    }

    @GetMapping(CREATE_URL)
    public String showCreateBookForm(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        return BOOK_CREATION_FORM;
    }

    @GetMapping(DELETE_URL)
    public String showDeleteBookForm() {
        return BOOK_DELETE_FORM;
    }

    /**
     * Добавление книги.
     *
     * @param bookDTO
     * @param redirectAttributes
     * @return
     */
    @PostMapping(CREATE_URL)
    @Transactional
    public String createBook(@ModelAttribute BookDTO bookDTO, RedirectAttributes redirectAttributes) {
        if (bookService.existsByIsbn(bookDTO.getIsbn())) {
            redirectAttributes.addFlashAttribute("bookCreationMessage", "Книга с таким isbn уже добавлена.");
        } else {
            bookDTO.setId(UUID.randomUUID().toString());
            Book addedBook = bookService.createBook(bookDTO);
            bookInstanceService.createBookInstances(addedBook, addedBook.getTotalNumber());
            redirectAttributes.addFlashAttribute("bookCreationMessage", "Книга успешно добавлена.");
        }
        return "redirect:" + BOOK_URL + CREATE_URL;
    }

    /**
     * Удаление книги.
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping(DELETE_URL)
    @Transactional
    public String deleteBook(@ModelAttribute DeleteBookOrInstanceForm form, RedirectAttributes redirectAttributes) {
        String isbn = form.getIsbn();
        if (bookService.existsByIsbn(isbn)) {
            Book book = bookService.findByIbsn(isbn);
            bookInstanceService.deleteAllByBookId(book.getId());
            bookService.deleteBookByIsbn(isbn);
            redirectAttributes.addFlashAttribute("bookDeleteMessage",
                "Книга " + book.getTitle() + " с isbn " + book.getIsbn() + " успешно удалена.");
        } else {
            redirectAttributes.addFlashAttribute("bookDeleteMessage", "Книги с таким isbn не существует.");
        }
        return "redirect:" + BOOK_URL + DELETE_URL;
    }

    @GetMapping(SHOW_URL)
    public String listBooks(@ModelAttribute("filter") BookFilterDTO filter, Model model) {
        List<Book> books;
        if (filter.isFilterSet()) {
            books = bookService.findBooksByFilter(filter); // Поиск книг по фильтру
        } else {
            books = bookService.findAllBooks();
        }
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return BOOK_LIST_FORM;
    }
}
