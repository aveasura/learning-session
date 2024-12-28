package org.learning.session.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final String[] accessLevel = {"/registration", "/login"};

    private static final String ACCOUNT_REDIRECT = "/account";
    private static final String LOGIN_REDIRECT = "/login";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Преобразование объектов в HTTP-контекст
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // Получить путь запроса пользователя
        String path = req.getRequestURI().substring(req.getContextPath().length());
        HttpSession session = req.getSession(false);

        // Проверяем, есть ли активная сессия
        boolean isAuthenticated = session != null && session.getAttribute("userId") != null;
        if (isAuthenticated) {

            // Если пользователь авторизован, запрещаем доступ к страницам авторизации
            if (List.of(accessLevel).contains(path)) {
                redirect(req, resp, ACCOUNT_REDIRECT, "Сессия активна, доступ к странице авторизации запрещен.");
                return;
            }

        } else {

            // Пользователь не авторизован, можно проверять доступ к защищенным ресурсам
            if (path.startsWith(ACCOUNT_REDIRECT)) {
                redirect(req, resp, LOGIN_REDIRECT, "Доступ запрещен. Авторизуйтесь для доступа к аккаунту.");
                return;
            }
        }

        // Если все проверки пройдены, пропускаем запрос дальше
        filterChain.doFilter(req, resp);
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String path, String message) throws IOException {
        System.out.println(message);
        resp.sendRedirect(req.getContextPath() + path);
    }
}
