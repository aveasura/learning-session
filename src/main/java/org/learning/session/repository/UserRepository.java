package org.learning.session.repository;

import org.learning.session.dao.UserDao;
import org.learning.session.dao.UserDaoImpl;
import org.learning.session.model.User;

import java.util.List;

// Класс предоставляет данные из DAO
public class UserRepository {

    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserById(long id) {
        return userDao.readById(id);
    }

    public User findUserByUsername(String username) {
        return userDao.readByUsername(username);
    }

    public long createUser(String username, String password) {
        return userDao.create(username, password);
    }

    public List<User> getAllUsers() {
        return userDao.readAll();
    }

    public void deleteByUserId(long userId) {
        userDao.delete(userId);
    }

    public void updateUser(long userId, String username, String password) {
        userDao.update(userId, username, password);
    }
}
