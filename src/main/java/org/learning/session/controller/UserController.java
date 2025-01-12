package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    private String accountPage(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("message", "Добро пожаловать " + username);
        return "account";
    }

    @GetMapping("/registration")
    private String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    private String doRegistration(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
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
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/login")
    private String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    private String doLogin(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpSession session,
                           Model model) {
        try {
            long userId = userService.authenticateUser(username, password);
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            return "redirect:/account";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // можно сделать через reqMapping
    @GetMapping("/account/update")
    private String updatePage() {
        return "update";
    }

    @PostMapping("/account/update")
    private String doUpdate(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        try {
            long userId = (long) session.getAttribute("userId");
            userService.updateUserById(userId, username, password);
            session.setAttribute("username", username);

            return "redirect:/account";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "update";
        }
    }

    @PostMapping("/account/delete")
    private String doDelete(HttpSession session, Model model) {
        try {
            long userId = (long) session.getAttribute("userId");
            userService.deleteByUserId(userId);
            session.invalidate();

            return "redirect:/home";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "account";
        }
    }

    @PostMapping("/account/logout")
    private String doLogout(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/home";
    }
}