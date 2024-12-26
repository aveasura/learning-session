package org.learning.session.model.repository;

import org.learning.session.model.entity.User;

import java.util.HashMap;
import java.util.Map;

// Todo реализовать через интерфейс
public class UserRepository {
    private final Map<Long, User> users = new HashMap<>(); // Ключ — userId
    private long nextId = 1;

    public long createUser(String username, String password) {

        User user = new User(nextId++, username, password);
        users.put(user.getId(), user);

        return user.getId();
    }

    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User findById(long userId) {
        return users.get(userId);
    }
}
