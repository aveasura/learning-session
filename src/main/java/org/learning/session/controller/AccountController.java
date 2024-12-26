package org.learning.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private static final String ACCOUNT_PAGE = "/WEB-INF/home/auth/account/account.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ACCOUNT_PAGE).forward(req, resp);
    }
}