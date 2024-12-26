package org.learning.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.learning.session.model.repository.UserRepository;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private static final String REGISTRATION_PAGE = "/WEB-INF/home/auth/registration.jsp";
    private static final String REGISTRATION_REDIRECT = "/registration";
    private static final String ACCOUNT_REDIRECT = "/account";

    // Todo: Потом заменить на DI в конструкторе
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Если юзер уже находится в уч записи, то не позволяем ему еще раз войти в нее -> просто редиректим сразу.
        if (session != null && session.getAttribute("username") != null) {
            redirect(req, resp, ACCOUNT_REDIRECT, "Сессия уже активна -> редирект на страницу аккаунта");
            return;
        }

        System.out.println("Пользователь не авторизован, показываем страницу регистрации.");
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Проверка активна ли сессия данного юзера(например если он пытается зарегистрироваться с X вкладок).
        if (session.getAttribute("userId") != null) {
            redirect(req, resp, ACCOUNT_REDIRECT, "Вы уже авторизированны, ID: " + session.getAttribute("userId"));
            return;
        }

        // Проверяем логин на заполненность. || Потом можно добавить проверку на занятость логина
        String username = req.getParameter("username");
        if (username == null || username.isBlank()) {
            redirect(req, resp, REGISTRATION_REDIRECT, "Логин не может быть пустым");
            return;
        }

        // Проверка пароля
        String password = req.getParameter("password");
        if (password == null || password.isBlank()) {
            redirect(req, resp, REGISTRATION_REDIRECT, "Пароль не может быть пустым");
            return;
        }

        // Допускаем к регистрации и устанавливаем аттрибуты сессии.
        long userId = userRepository.createUser(username, password);

        session.setAttribute("userId", userId);
        session.setAttribute("username", username);

        redirect(req, resp, ACCOUNT_REDIRECT, "Пользователь с ID: " + userId + " успешно зарегистрирован.");

        // просто для примера, работа метода(должен быть в сервисе!):
        // User user = userRepository.findById(userId);
        // System.out.println(user.getUsername());
    }

    // Утилитарный метод
    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}