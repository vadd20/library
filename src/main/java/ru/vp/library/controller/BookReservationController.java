package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.ISSUE_LIST_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.RESERVATION_ACTION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.RESERVATION_CHANGE_EXPIRE_DATE_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.RESERVATION_CREATE_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.RESERVATION_LIST_FORM;
import static ru.vp.library.constants.UrlNames.CHANGE_EXPIR_DATE_URL;
import static ru.vp.library.constants.UrlNames.CREATE_URL;
import static ru.vp.library.constants.UrlNames.RESERVATION_URL;
import static ru.vp.library.constants.UrlNames.SHOW_URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vp.library.domain.BookInstance;
import ru.vp.library.domain.BookReservation;
import ru.vp.library.domain.Client;
import ru.vp.library.domain.ReservationStatus;
import ru.vp.library.dto.IssueDTO;
import ru.vp.library.dto.IssueFilterDTO;
import ru.vp.library.dto.ReservationDTO;
import ru.vp.library.dto.ReservationFilterDTO;
import ru.vp.library.service.BookInstanceService;
import ru.vp.library.service.BookIssueService;
import ru.vp.library.service.BookReservationService;
import ru.vp.library.service.ClientService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */
@Controller
@RequestMapping(RESERVATION_URL)
public class BookReservationController {

    private final BookReservationService bookReservationService;
    private final BookIssueService bookIssueService;
    private final ClientService clientService;
    private final BookInstanceService bookInstanceService;

    public BookReservationController(BookReservationService bookReservationService,
                                     BookIssueService bookIssueService,
                                     ClientService clientService,
                                     BookInstanceService bookInstanceService) {
        this.bookReservationService = bookReservationService;
        this.bookIssueService = bookIssueService;
        this.clientService = clientService;
        this.bookInstanceService = bookInstanceService;
    }

    @GetMapping
    public String reservationActions() {
        return RESERVATION_ACTION_FORM;
    }

    @GetMapping(CREATE_URL)
    public String showReservationsForm(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return RESERVATION_CREATE_FORM;
    }

    @GetMapping(CHANGE_EXPIR_DATE_URL)
    public String showChangeExpireDateForm(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return RESERVATION_CHANGE_EXPIRE_DATE_FORM;
    }

    @PostMapping(CREATE_URL)
    public String reserveBook(@ModelAttribute ReservationDTO reservationDTO, RedirectAttributes redirectAttributes) {
        String isbn = reservationDTO.getIsbn();
        String readerTicketNumber = reservationDTO.getReaderTicketNumber();
        if (bookInstanceService.existsById(isbn) && clientService.existsByReaderTicketNumber(readerTicketNumber)) {
            BookInstance bookInstance = bookInstanceService.findByIsbn(isbn).get();
            if (!bookInstance.getIsAvailable()) {
                reservationDTO.setId(UUID.randomUUID().toString());
                BookReservation bookReservation = bookReservationService.createBookReservation(reservationDTO);
                redirectAttributes.addFlashAttribute("bookReserveMessage", "Книга зарезервирована");
            } else {
                redirectAttributes
                    .addFlashAttribute("bookReserveMessage", "Книгу невозможно зарезервировать, так как она свободна");
            }
        } else {
            redirectAttributes.addFlashAttribute("bookReserveMessage", "Введены неверные данные");
        }
        return "redirect:" + RESERVATION_URL + CREATE_URL;
    }

    @PostMapping(CHANGE_EXPIR_DATE_URL)
    public String changeExpirationDate(@ModelAttribute ReservationDTO reservationDTO,
                                       RedirectAttributes redirectAttributes) {
        String isbn = reservationDTO.getIsbn();
        String readerTicketNumber = reservationDTO.getReaderTicketNumber();
        Optional<Client> client = clientService.findByReaderTicketNumber(readerTicketNumber);
        if (client.isEmpty()) {
            redirectAttributes
                .addFlashAttribute("bookChangeExpireMessage", "Клиента с таким читательским билетом не существует");
        } else if (bookReservationService.existsByBookInstanceIdAndClientId(isbn, client.get().getId())) {
            BookReservation bookReservation = bookReservationService.findByBookInstanceId(isbn).get();
            bookReservation.setExpirationDate(reservationDTO.getExpirationDate());
            bookReservationService.save(bookReservation);
            redirectAttributes
                .addFlashAttribute("bookChangeExpireMessage", "Дата окончания срока бронирования изменена");
        } else {
            redirectAttributes
                .addFlashAttribute("bookChangeExpireMessage", "Брони с такими данными не существует");
        }
        return "redirect:" + RESERVATION_URL + CHANGE_EXPIR_DATE_URL;
    }

    @GetMapping(SHOW_URL)
    @Transactional
    public String listReservations(@ModelAttribute("filter") ReservationFilterDTO filter, Model model) {
        List<ReservationDTO> reservations;
        if (filter.isFilterSet()) {
            reservations = bookReservationService.findReservationsByFilter(filter);
        } else {
            reservations = bookReservationService.findAllBookReservations();
        }
        model.addAttribute("reservations", reservations);
        model.addAttribute("filter", filter);
        return RESERVATION_LIST_FORM;
    }
}
