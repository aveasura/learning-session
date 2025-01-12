package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userId")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    private String accountPage() {
        return "account";
    }

    @GetMapping("/registration")
    private String registrationPage() {
        return "registration";
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
            return "registration";
        }
    }

    @GetMapping("/login")
    private String loginPage() {
        return "login";
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
            return "login";
        }
    }

    @GetMapping("/update")
    private String updatePage() {
        return "update";
    }

    // используем @ModelAttribute для извлечения userId из сессии
    @PostMapping("/update")
    private String doUpdate(@RequestParam String username,
                            @RequestParam String password,
                            @SessionAttribute("userId") Long userId,
                            HttpSession session,
                            Model model) {
        try {
            userService.updateUserById(userId, username, password);
            session.setAttribute("username", username);

            // tmp
            model.addAttribute("success", "Пользователь свои данные. ID: " + userId);

            return "redirect:/account";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "update";
        }
    }

    @PostMapping("/delete")
    private String doDelete(@SessionAttribute("userId") Long userId,
                            HttpSession session,
                            Model model) {
        try {
            userService.deleteByUserId(userId);
            session.invalidate();

            // tmp
            model.addAttribute("success", "Аккаунт удален. ID: " + userId);

            return "redirect:/home";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "account";
        }
    }

    @PostMapping("/logout")
    private String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}