package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/account")
public class DeleteAccountController {

    private final UserService userService;

    public DeleteAccountController(UserService userService) {
        this.userService = userService;
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
            return "/auth/account/account";
        }
    }
}