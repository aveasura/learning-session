package org.learning.session.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс отвечает за подключение к БД
 */
public class DatabaseConnection {
    private static final String DB_URL = ConfigUtil.get("db.url");
    private static final String DB_USER = ConfigUtil.get("db.username");
    private static final String DB_PASSWORD = ConfigUtil.get("db.password");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки драйвера PostgreSQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
