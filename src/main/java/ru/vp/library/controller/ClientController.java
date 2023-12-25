package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.CLIENT_ACTION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.CLIENT_CREATION_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.CLIENT_LIST_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.CLIENT_REMOVING_FORM;
import static ru.vp.library.constants.ThymeleafTemplateSystemNames.RESERVATION_LIST_FORM;
import static ru.vp.library.constants.UrlNames.BOOK_URL;
import static ru.vp.library.constants.UrlNames.CLIENT_URL;
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
import ru.vp.library.domain.Client;
import ru.vp.library.dto.ClientDTO;
import ru.vp.library.dto.ReservationDTO;
import ru.vp.library.dto.ReservationFilterDTO;
import ru.vp.library.service.ClientService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.25
 */

@Controller
@RequestMapping(CLIENT_URL)
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String clientActions() {
        return CLIENT_ACTION_FORM;
    }

    @GetMapping(CREATE_URL)
    public String showCreateClientForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return CLIENT_CREATION_FORM;
    }

    @GetMapping(DELETE_URL)
    public String deleteClientForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return CLIENT_REMOVING_FORM;
    }

    @PostMapping(CREATE_URL)
    @Transactional
    public String createClient(@ModelAttribute ClientDTO clientDTO, RedirectAttributes redirectAttributes) {
        if (clientService.existsByReaderTicketNumber(clientDTO.getReaderTicketNumber())) {
            redirectAttributes.addFlashAttribute("clientCreationMessage",
                "Клиента с таким номером читательского билета уже существует. Введите другой номер");
        } else {
            clientService.createClient(clientDTO);
            redirectAttributes.addFlashAttribute("clientCreationMessage", "Клиент успешно добавлен");
        }
        return "redirect:" + CLIENT_URL + CREATE_URL;
    }

    @PostMapping(DELETE_URL)
    @Transactional
    public String deleteBook(@ModelAttribute ClientDTO clientDTO, RedirectAttributes redirectAttributes) {

        if (clientService.existsByReaderTicketNumber(clientDTO.getReaderTicketNumber())) {
            Client client = clientService.findByReaderTicketNumber(clientDTO.getReaderTicketNumber()).get();
            clientService.deleteById(client.getId());
            redirectAttributes.addFlashAttribute("clientDeleteMessage", "Клиент успешно удален.");
        } else {
            redirectAttributes
                .addFlashAttribute("clientDeleteMessage", "Клиента с таким номером читательского билета не существует");
        }
        return "redirect:" + CLIENT_URL + DELETE_URL;
    }

    @GetMapping(SHOW_URL)
    @Transactional
    public String listReservations(@ModelAttribute("filter") ClientDTO filter, Model model) {
        List<ClientDTO> clients;
        if (filter.isFilterSet()) {
            clients = clientService.findClientsByFilter(filter);
        } else {
            clients = clientService.findAllClients();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("filter", filter);
        return CLIENT_LIST_FORM;
    }
}
