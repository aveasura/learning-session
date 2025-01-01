package org.learning.session.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.learning.session.service.UserService;
import org.learning.session.service.ValidationException;
import org.learning.session.validation.LoginValidator;

import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final String LOGIN_PAGE = "/WEB-INF/home/auth/login.jsp";
    private static final String LOGIN_REDIRECT = "/login";
    private static final String ACCOUNT_REDIRECT = "/account";

    private UserService userService;
    // private LoginValidator loginValidator;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        // this.loginValidator = (LoginValidator) context.getAttribute("loginValidator");

        this.userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            long userId = userService.authenticateUser(username, password);
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            redirect(req, resp, ACCOUNT_REDIRECT, "Пользователь успешно авторизован. ID: " + userId);
        } catch (ValidationException e) {
            // Если ошибка валидации (например, логин уже занят), передаем сообщение в JSP, и отображаем (forward)
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}