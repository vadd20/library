package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_ACTION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_CREATION_FORM;
import static ru.vp.library.constants.UrlNames.BOOK_URL;
import static ru.vp.library.constants.UrlNames.CREATE_URL;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.Book;
import ru.vp.library.dto.BookDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return bookService.findBookById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

