package org.learning.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final String LOGIN_PAGE = "/WEB-INF/home/auth/login.jsp";
    private static final String ACCOUNT_REDIRECT = "/account";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && req.getParameter("userId") != null) {
            redirect(req, resp, ACCOUNT_REDIRECT, "Вы уже авторизованы: " + req.getParameter("userId"));
            return;
        }

        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }


    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}
