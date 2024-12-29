package org.learning.session.repository;

import org.learning.session.dao.UserDaoImpl;
import org.learning.session.model.User;

import java.util.List;

// Класс предоставляет данные из DAO
public class UserRepository {

    private final UserDaoImpl userDaoImpl;

    public UserRepository(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public User findUserById(long id) {
        return userDaoImpl.readById(id);
    }

    public User findUserByUsername(String username) {
        return userDaoImpl.readByUsername(username);
    }

    public long createUser(String username, String password) {
        return userDaoImpl.create(username, password);
    }

    public List<User> getAllUsers() {
        return userDaoImpl.readAll();
    }

    public void deleteByUserId(long userId) {
        userDaoImpl.delete(userId);
    }
}
