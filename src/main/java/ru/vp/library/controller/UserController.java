package ru.vp.library.controller;

import static ru.vp.library.constants.ThymeleafTemplateSystemNames.CLIENT_LIST_FORM;
import static ru.vp.library.constants.UrlNames.ADMIN_URL;
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
import ru.vp.library.domain.User;
import ru.vp.library.dto.UserDTO;
import ru.vp.library.service.user.UserService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.25
 */
@Controller
@RequestMapping(ADMIN_URL)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userActions() {
        return "userAction";
    }

    @GetMapping(CREATE_URL)
    public String showCreateUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "userCreation";
    }

    @GetMapping(DELETE_URL)
    public String deleteUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "userRemoving";
    }

    @PostMapping(CREATE_URL)
    @Transactional
    public String createUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        if (userService.existsByLogin(userDTO.getLogin())) {
            redirectAttributes.addFlashAttribute("userCreationMessage",
                "Пользователь с таким логином уже существует. Введите другой логин");
        } else {
            userService.createUser(userDTO);
            redirectAttributes.addFlashAttribute("userCreationMessage", "Пользователь успешно добавлен");
        }
        return "redirect:" + ADMIN_URL + CREATE_URL;
    }

    @PostMapping(DELETE_URL)
    @Transactional
    public String deleteBook(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {

        if (userService.existsByLogin(userDTO.getLogin())) {
            User user = userService.findByLogin(userDTO.getLogin());
            userService.deleteById(user.getId());
            redirectAttributes.addFlashAttribute("userDeleteMessage", "Пользователь успешно удален.");
        } else {
            redirectAttributes
                .addFlashAttribute("userDeleteMessage", "Пользователя с таким логином не существует");
        }
        return "redirect:" + ADMIN_URL + DELETE_URL;
    }

    @GetMapping(SHOW_URL)
    @Transactional
    public String showUserList(@ModelAttribute("filter") UserDTO filter, Model model) {
        List<UserDTO> users;
        if (filter.isFilterSet()) {
            users = userService.findUsersByFilter(filter);
        } else {
            users = userService.findAllUsers();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter", filter);
        return "userList";
    }
}