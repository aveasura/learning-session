package org.learning.session.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.learning.session.dao.UserDaoImpl;
import org.learning.session.repository.UserRepository;
import org.learning.session.service.UserService;
import org.learning.session.util.DataSourceConfig;

import javax.sql.DataSource;

@WebListener
public class DependencyInjectionListener implements ServletContextListener {

    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        // Создаем подключение к бд.
        dataSource = DataSourceConfig.getDataSource();

        // DI
        // LoginValidator loginValidator = new LoginValidatorImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
        UserRepository userRepository = new UserRepository(userDaoImpl);
        UserService userService = new UserService(userRepository);

        // context.setAttribute("loginValidator", loginValidator);
        context.setAttribute("userService", userService);
    }
}
