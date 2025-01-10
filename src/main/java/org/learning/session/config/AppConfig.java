package org.learning.session.config;

import org.learning.session.dao.UserDao;
import org.learning.session.dao.UserDaoImpl;
import org.learning.session.repository.UserRepository;
import org.learning.session.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// Здесь находятся настройки, относящиеся к работе с базой данных и бизнес-логикой
// Сканируем только пакеты, которые относятся к бизнес-логике, DAO и инфраструктуре
@Configuration
@ComponentScan(basePackages = {
        "org.learning.session.service",
        "org.learning.session.repository",
        "org.learning.session.dao"})
public class AppConfig {

    @Bean
    public DataSource getDataSource() {
        final String DB_URL = ConfigUtil.get("db.url");
        final String DB_USER = ConfigUtil.get("db.username");
        final String DB_PASSWORD = ConfigUtil.get("db.password");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public UserDao userDao(DataSource dataSource) {
        return new UserDaoImpl(dataSource);
    }

    @Bean
    public UserRepository userRepository(UserDao userDao) {
        return new UserRepository(userDao);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}