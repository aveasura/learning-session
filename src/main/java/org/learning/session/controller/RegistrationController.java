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
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    private String registrationPage() {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    private String doRegistration(@RequestParam String username,
                                  @RequestParam String password,
                                  HttpSession session,
                                  Model model) {
        try {
            // создать юзера
            long userId = userService.createUser(username, password);

            // сохранить его данные в сессии
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            // сообщить об успешной регистрации (tmp?)
            model.addAttribute("success", "Пользователь успешно зарегистрирован. ID: " + userId);

            // и перенаправляем на страницу его аккаунта
            return "redirect:/account";
        } catch (ValidationException e) {
            // Передать сообщение об ошибке обратно на страницу регистрации
            model.addAttribute("error", e.getMessage());
            return "/auth/registration";
        }
    }
}