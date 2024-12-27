package org.learning.session.dao;

import org.learning.session.model.User;

import java.util.HashMap;
import java.util.Map;

// Класс напрямую общается с бд и предоставляет данные репозиторию (CRUD)
public class UserDAO {
    private final Map<Long, User> users = new HashMap<>(); // Ключ — userId
    private long nextId = 1;

    public long createUser(String username, String password) {

        User user = new User(nextId++, username, password);
        users.put(user.getId(), user);

        return user.getId();
    }

    public User findUserByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User findUserById(long userId) {
        return users.get(userId);
    }

    public boolean isUserExist(User user) {
        return users.containsValue(user);
    }
}
