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

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private static final String ACCOUNT_PAGE = "/WEB-INF/home/auth/account/account.jsp";
    private static final String ACCOUNT_REDIRECT = "/account";
    private static final String HOME_REDIRECT = "/home";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // запрет на посещение страницы для не залогиненных пользователей.
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            redirect(req, resp, HOME_REDIRECT, "Чтобы попасть на эту страницу зайдите в аккаунт или " +
                    "зарегистрируйтесь");
            return;
        }

        req.getRequestDispatcher(ACCOUNT_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long userId = (long) session.getAttribute("userId");

        try {
            userService.deleteByUserId(userId);
            session.invalidate();
            resp.sendRedirect(HOME_REDIRECT);
        } catch (ValidationException e) {
            redirect(req, resp, ACCOUNT_REDIRECT, e.getMessage());
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}