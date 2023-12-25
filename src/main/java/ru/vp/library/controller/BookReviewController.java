package ru.vp.library.controller;

import static ru.vp.library.constants.UrlNames.CREATE_URL;
import static ru.vp.library.constants.UrlNames.DELETE_URL;
import static ru.vp.library.constants.UrlNames.SHOW_URL;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.BookReview;
import ru.vp.library.dto.ReviewDTO;
import ru.vp.library.service.BookReviewService;
import ru.vp.library.service.BookService;
import ru.vp.library.service.ClientService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */

@Controller
@RequestMapping("/review")
public class BookReviewController {

    private final BookReviewService bookReviewService;
    private final BookService bookService;
    private final ClientService clientService;

    public BookReviewController(BookReviewService bookReviewService, BookService bookService,
                                ClientService clientService) {
        this.bookReviewService = bookReviewService;
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @GetMapping
    public String reviewActions() {
        return "reviewAction";
    }

    @GetMapping(CREATE_URL)
    public String showCreateReviewForm(Model model) {
        model.addAttribute("reviewDTO", new ReviewDTO());
        return "reviewCreation";
    }

    @GetMapping(DELETE_URL)
    public String deleteReviewForm(Model model) {
        model.addAttribute("reviewDTO", new ReviewDTO());
        return "reviewRemoving";
    }

    @PostMapping(CREATE_URL)
    @Transactional
    public String createReview(@ModelAttribute ReviewDTO reviewDTO, RedirectAttributes redirectAttributes) {
        if (bookService.existsByIsbn(reviewDTO.getIsbn()) &&
            clientService.existsByReaderTicketNumber(reviewDTO.getReaderTicketNumber())) {
            bookReviewService.createBookReview(reviewDTO);
            redirectAttributes.addFlashAttribute("reviewCreationMessage",
                "Отзыв успешно добавлен.");
        } else {
            redirectAttributes
                .addFlashAttribute("reviewCreationMessage", "Отзыв от такого клиента по данной книге уже существует.");
        }
        return "redirect:" + "/review" + CREATE_URL;
    }

    @PostMapping(DELETE_URL)
    @Transactional
    public String deleteReview(@ModelAttribute ReviewDTO reviewDTO, RedirectAttributes redirectAttributes) {
        if (!bookService.existsByIsbn(reviewDTO.getIsbn()) ||
            !clientService.existsByReaderTicketNumber(reviewDTO.getReaderTicketNumber())) {
            redirectAttributes
                .addFlashAttribute("reviewDeleteMessage", "Введенные данные некорректны.");
        } else if (bookReviewService.existsByBookIdAndClientId(bookService.findByIbsn(reviewDTO.getIsbn()).getId(),
            clientService.findByReaderTicketNumber(reviewDTO.getReaderTicketNumber()).get().getId())) {
            BookReview review =
                bookReviewService.findByBookIdAndClientId(bookService.findByIbsn(reviewDTO.getIsbn()).getId(),
                    clientService.findByReaderTicketNumber(reviewDTO.getReaderTicketNumber()).get().getId());
            bookReviewService.deleteBookReview(review.getId());
            redirectAttributes.addFlashAttribute("reviewDeleteMessage", "Отзыв успешно удален.");
        } else {
            redirectAttributes
                .addFlashAttribute("reviewDeleteMessage", "Отзыва с такими параметрами не существует.");
        }
        return "redirect:" + "/review" + DELETE_URL;
    }

    @GetMapping(SHOW_URL)
    @Transactional
    public String listReservations(@ModelAttribute("filter") ReviewDTO filter, Model model) {
        List<ReviewDTO> reviews;
        if (filter.isFilterSet()) {
            reviews = bookReviewService.findReviewsByFilter(filter);
        } else {
            reviews = bookReviewService.findAllReviews();
        }
        model.addAttribute("reviews", reviews);
        model.addAttribute("filter", filter);
        return "reviewList";
    }
}
