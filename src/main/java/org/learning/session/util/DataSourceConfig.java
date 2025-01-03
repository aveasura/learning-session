package org.learning.session.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Класс отвечает за подключение к БД
 */

public class DataSourceConfig {

    private static final String DB_URL = ConfigUtil.get("db.url");
    private static final String DB_USER = ConfigUtil.get("db.username");
    private static final String DB_PASSWORD = ConfigUtil.get("db.password");

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");

        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }
}