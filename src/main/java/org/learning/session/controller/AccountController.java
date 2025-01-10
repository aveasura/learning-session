package org.learning.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account")
    private String accountPage() {
        return "/auth/account/account";
    }
}