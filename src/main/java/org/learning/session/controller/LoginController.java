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

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final String LOGIN_PAGE = "/WEB-INF/home/auth/login.jsp";
    private static final String LOGIN_REDIRECT = "/login";
    private static final String ACCOUNT_REDIRECT = "/account";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        // Получить зависимость из контекста
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("userId") != null) {
            redirect(req, resp, ACCOUNT_REDIRECT, "Сессия уже активна -> редирект на страницу аккаунта");
            return;
        }

        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        // Проверка активна ли сессия для данного юзера(попытка лоигн с X вкладок). || подумать: фильтры
        if (session.getAttribute("userId") != null) {
            redirect(req, resp, ACCOUNT_REDIRECT, "Вы уже авторизованы. ID: " + session.getAttribute("userId"));
            return;
        }

        try {
            long userId = userService.authenticateUser(username, password);
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            redirect(req, resp, ACCOUNT_REDIRECT, "Пользователь успешно авторизован. ID: " + userId);
        } catch (ValidationException e) {
            redirect(req, resp, LOGIN_REDIRECT, e.getMessage());
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}
