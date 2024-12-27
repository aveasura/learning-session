package org.learning.session.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.learning.session.dao.UserDAO;
import org.learning.session.repository.UserRepository;
import org.learning.session.service.UserService;

@WebListener
public class DependencyInjectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // DI
        UserDAO userDAO = new UserDAO();
        UserRepository userRepository = new UserRepository(userDAO);
        UserService userService = new UserService(userRepository);

        context.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Очистка ресурсов при остановке приложения, если необходимо
    }
}
