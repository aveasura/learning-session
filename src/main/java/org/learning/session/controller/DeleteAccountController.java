package org.learning.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;

import java.io.IOException;

@WebServlet("/account/delete")
public class DeleteAccountController extends HttpServlet {
    private static final String HOME_REDIRECT = "/home";
    private static final String ACCOUNT_REDIRECT = "/account";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute("userId");

        try {
            userService.deleteByUserId(userId);
            session.invalidate();
            redirect(req, resp, HOME_REDIRECT, "Сессия удалена, аккаунт удален, редирект на главную страницу");
        } catch (ValidationException e) {
            redirect(req, resp, ACCOUNT_REDIRECT, e.getMessage());
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}
