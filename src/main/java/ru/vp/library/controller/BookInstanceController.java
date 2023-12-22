package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_DELETE_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_INSTANCE_CREATION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.BOOK_INSTANCE_DELETE_FORM;
import static ru.vp.library.constants.UrlNames.BOOK_URL;
import static ru.vp.library.constants.UrlNames.CREATE_URL;
import static ru.vp.library.constants.UrlNames.DELETE_URL;
import static ru.vp.library.constants.UrlNames.INSTANCE_URL;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.Book;
import ru.vp.library.dto.BookInstanceDTO;
import ru.vp.library.dto.DeleteBookOrInstanceForm;
import ru.vp.library.service.BookInstanceService;
import ru.vp.library.service.BookService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
@Controller
@RequestMapping(BOOK_URL + INSTANCE_URL)
public class BookInstanceController {

    private final BookService bookService;
    private final BookInstanceService bookInstanceService;

    public BookInstanceController(BookInstanceService bookInstanceService, BookService bookService) {
        this.bookInstanceService = bookInstanceService;
        this.bookService = bookService;
    }

    @GetMapping(CREATE_URL)
    public String showCreateBookForm(Model model) {
        model.addAttribute("bookInstanceDTO", new BookInstanceDTO());
        return BOOK_INSTANCE_CREATION_FORM;
    }

    @GetMapping(DELETE_URL)
    public String showDeleteBookForm() {
        return BOOK_INSTANCE_DELETE_FORM;
    }

    /**
     * Добавление экземпляра существующей книги.
     *
     * @param bookInstanceDTO
     * @param redirectAttributes
     * @return
     */
    @PostMapping(CREATE_URL)
    public String createBookInstance(@ModelAttribute BookInstanceDTO bookInstanceDTO,
                                     RedirectAttributes redirectAttributes) {
        if (bookService.existsByIsbn(bookInstanceDTO.getIsbn())) {
            Book book = bookService.findByIbsn(bookInstanceDTO.getIsbn());
            List<Integer> addedInstancesNumber =
                bookInstanceService.createBookInstances(book, bookInstanceDTO.getNumberOfInstances());
            redirectAttributes.addFlashAttribute("bookInstanceCreationMessage",
                "Добавлены экземпляры под номерами: " + addedInstancesNumber);
        } else {
            redirectAttributes.addFlashAttribute("bookInstanceCreationMessage", "Книги с таким isbn не существует");
        }
        return "redirect:" + BOOK_URL + INSTANCE_URL + CREATE_URL;
    }

    @PostMapping(DELETE_URL)
    @Transactional
    public String deleteBookInstance(@ModelAttribute DeleteBookOrInstanceForm form, RedirectAttributes redirectAttributes) {
        String isbn = form.getIsbn();
        if (bookInstanceService.existsById(isbn)) {
            bookInstanceService.deleteBookInstanceById(isbn);
            redirectAttributes.addFlashAttribute("bookInstanceDeleteMessage",
                "Экземпляр с isbn " + isbn + " успешно удален.");
        } else {
            redirectAttributes.addFlashAttribute("bookInstanceDeleteMessage", "Экземпляр с таким isbn не существует.");
        }
        return "redirect:" + BOOK_URL + INSTANCE_URL + DELETE_URL;
    }
}
