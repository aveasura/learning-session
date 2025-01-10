package org.learning.session.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class LogoutController {

    @PostMapping("/logout")
    private String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}