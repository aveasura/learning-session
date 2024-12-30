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

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private static final String REGISTRATION_PAGE = "/WEB-INF/home/auth/registration.jsp";
    private static final String REGISTRATION_REDIRECT = "/registration";
    private static final String ACCOUNT_REDIRECT = "/account";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            long userId = userService.createUserAndGetId(username, password);
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            redirect(req, resp, ACCOUNT_REDIRECT, "Пользователь успешно зарегистрирован. ID: " + userId);
        } catch (ValidationException e) {
            // redirect(req, resp, REGISTRATION_REDIRECT, e.getMessage());

            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}