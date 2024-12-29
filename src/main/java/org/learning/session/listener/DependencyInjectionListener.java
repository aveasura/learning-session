package org.learning.session.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.learning.session.dao.UserDaoImpl;
import org.learning.session.repository.UserRepository;
import org.learning.session.service.UserService;
import org.learning.session.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class DependencyInjectionListener implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        try {
            // Создаем подключение к бд.
            connection = DatabaseConnection.getConnection();

            // DI
            UserDaoImpl userDaoImpl = new UserDaoImpl(connection);
            UserRepository userRepository = new UserRepository(userDaoImpl);
            UserService userService = new UserService(userRepository);

            context.setAttribute("userService", userService);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить подключение к базе данных", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        // Закрываем соединение при остановке приложения
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Добавить ошибки в логгер
                // ...
                e.printStackTrace();
            }
        }
    }
}
