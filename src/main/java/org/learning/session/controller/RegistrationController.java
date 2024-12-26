package org.learning.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private final static String ACCOUNT = "/WEB-INF/home/auth/account/account.jsp";
    private final static String ACCOUNT_REDIRECT = "account";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Если юзер уже находится в уч записи, то не позволяем ему еще раз войти в нее -> просто редиректим сразу.
        if (session != null && session.getAttribute("username") != null) {
            System.out.println("Сессия уже активна -> страница акка");
            resp.sendRedirect(req.getContextPath() + ACCOUNT_REDIRECT);
            return;
        }

        System.out.println("Пользователь не авторизован, показываем страницу регистрации.");
        req.getRequestDispatcher("/WEB-INF/home/auth/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // Проверка активна ли сессия данного юзера(например если он пытается зарегистрироваться с X вкладок).
        if (session.getAttribute("username") != null) {
            System.out.println("Пользователь " + session.getAttribute("username") + " уже авторизован");
            resp.sendRedirect(req.getContextPath() + ACCOUNT_REDIRECT);
            return;
        }

        String username = req.getParameter("username");
        // Проверяем логин на заполненность.
        if (username == null || username.isBlank()) {
            System.out.println("Имя не может быть пустым");
            resp.sendRedirect(req.getContextPath() + ACCOUNT_REDIRECT);
            return;
        }

        // Допускаем к регистрации и устанавливаем аттрибут сессии.
        session.setAttribute("username", username);
        System.out.println("сохранен: " + username);

        resp.sendRedirect(req.getContextPath() + ACCOUNT_REDIRECT);
    }
}