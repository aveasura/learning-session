package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userId")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    private String loginPage() {
        return "/auth/login";
    }

    @PostMapping("/login")
    private String doLogin(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
        try {
            // логинимся
            long userId = userService.authenticateUser(username, password);
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            // tmp? (RedirectAttributes)
            model.addAttribute("success", "Пользователь успешно авторизован. ID: " + userId);

            return "redirect:/account";
        } catch (ValidationException e) {
            // возвращаем ошибку
            model.addAttribute("error", e.getMessage());
            return "/auth/login";
        }
    }
}