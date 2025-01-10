package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@SessionAttributes("userId")  // атрибут, который будет храниться в сессии
public class UpdateAccountController {

    private final UserService userService;

    public UpdateAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/update")
    private String updatePage() {
        return "/auth/update";
    }

    // используем @ModelAttribute для извлечения userId из сессии
    @PostMapping("/update")
    private String doUpdate(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("userId") Long userId,
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
            return "/auth/update";
        }

    }
}