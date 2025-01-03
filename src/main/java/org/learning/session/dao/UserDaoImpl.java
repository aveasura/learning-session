package org.learning.session.dao;

import org.learning.session.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

// Класс напрямую общается с бд и предоставляет данные репозиторию (CRUD)
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long create(String username, String password) {
        String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?) RETURNING user_id";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    @Override
    public List<User> readAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User readByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;  // Или выбросить своё исключение
        }
//        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
    }


    @Override
    public User readById(long id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    @Override
    public void update(long id, String username, String password) {
        String sql = "UPDATE users SET user_name = ?, user_password = ? WHERE user_id = ?";

        // Использование id в запросе необходимо, чтобы точно указать, какую запись в таблице нужно обновить.
        jdbcTemplate.update(sql, username, password, id);
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }
}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new User(
                rs.getLong("user_id"),
                rs.getString("user_name"),
                rs.getString("user_password")
        );
    }
}