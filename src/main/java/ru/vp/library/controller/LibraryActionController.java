package ru.vp.library.controller;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vp.library.domain.User;
import ru.vp.library.repository.UserRepository;
import ru.vp.library.service.user.AuthService;
import ru.vp.library.service.user.UserService;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.25
 */
@Controller
@RequestMapping("/")
public class LibraryActionController {

    private final UserService userService;

    public LibraryActionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String actions(Model model) {
        Optional<User> user = userService.findUserById(AuthService.getCurrentUserId());
        model.addAttribute("role", user.get().getRole());
        return "main";
    }
}
