package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.ISSUANCE_ACTION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.ISSUANCE_ISSUE_FORM;
import static ru.vp.library.constants.UrlNames.BOOK_URL;
import static ru.vp.library.constants.UrlNames.CREATE_URL;
import static ru.vp.library.constants.UrlNames.ISSUANCE_URL;
import static ru.vp.library.constants.UrlNames.ISSUE_URL;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.BookInstance;
import ru.vp.library.domain.BookIssue;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.service.BookInstanceService;
import ru.vp.library.service.BookIssueService;
import ru.vp.library.service.ClientService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */
@Controller
@RequestMapping(ISSUANCE_URL)
public class BookIssueController {

    private final BookIssueService bookIssueService;
    private final BookInstanceService bookInstanceService;
    private final ClientService clientService;

    public BookIssueController(BookIssueService bookIssueService,
                               BookInstanceService bookInstanceService, ClientService clientService) {
        this.bookIssueService = bookIssueService;
        this.bookInstanceService = bookInstanceService;
        this.clientService = clientService;
    }

    @GetMapping
    public String issueActions() {
        return ISSUANCE_ACTION_FORM;
    }

    @GetMapping(ISSUE_URL)
    public String showIssueBookForm(Model model) {
        model.addAttribute("issueDTO", new IssueDTO());
        return ISSUANCE_ISSUE_FORM;
    }

    @PostMapping(ISSUE_URL)
    @Transactional
    public String issueBook(@ModelAttribute IssueDTO issueDTO, RedirectAttributes redirectAttributes) {
        String isbn = issueDTO.getIsbn();
        String readerTicketNumber = issueDTO.getReaderTicketNumber();
        if (bookInstanceService.existsById(isbn) || clientService.existsByReaderTicketNumber(readerTicketNumber)) {
            BookInstance bookInstance = bookInstanceService.findByIsbn(isbn).get();
            if (bookInstance.getIsAvailable()) {
                issueDTO.setId(UUID.randomUUID().toString());
                BookIssue addedIssue = bookIssueService.createBookIssue(issueDTO);
                bookInstance.setIsAvailable(false);
                bookInstanceService.save(bookInstance);
                redirectAttributes.addFlashAttribute("bookIssueMessage", "Выдача осуществлена");
            } else {
                redirectAttributes.addFlashAttribute("bookIssueMessage", "Книга уже занята. Вы можете ее забронировать");
            }
        } else {
            redirectAttributes.addFlashAttribute("bookIssueMessage", "Введены неверные данные");
        }
        return "redirect:" + ISSUANCE_URL + ISSUE_URL;
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookIssue> getBookIssueById(@PathVariable String id) {
        return bookIssueService.findBookIssueById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookIssue createBookIssue(@RequestBody BookIssue bookIssue) {
        return bookIssueService.saveBookIssue(bookIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookIssue(@PathVariable String id) {
        bookIssueService.deleteBookIssue(id);
        return ResponseEntity.ok().build();
    }
}
