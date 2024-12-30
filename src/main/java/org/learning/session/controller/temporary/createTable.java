package org.learning.session.controller.temporary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.learning.session.dao.UserDaoImpl;
import org.learning.session.repository.UserRepository;
import org.learning.session.service.UserService;

import java.io.IOException;

// Это временный класс, будет удален позже
@WebServlet("/createtable")
public class createTable extends HttpServlet {
    private static final String HOME_PAGE = "/WEB-INF/home/home.jsp";

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.createTable();

        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }
}