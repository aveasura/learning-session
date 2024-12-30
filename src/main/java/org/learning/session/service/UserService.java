package org.learning.session.service;

import org.learning.session.model.User;
import org.learning.session.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUserById(long userId, String username, String password) throws ValidationException {

        User currentUser = userRepository.findUserById(userId);
        if (currentUser != null && !currentUser.getUsername().equals(username)) {
            validateLoginExistence(username, false);
        }

        validatePassword(password);

        userRepository.updateUser(userId, username, password);
    }

    public long createUserAndGetId(String username, String password) throws ValidationException {

        validateLoginExistence(username, false);
        validatePassword(password);

        return userRepository.createUser(username, password);
    }

    public long authenticateUser(String username, String password) throws ValidationException {
        User user = userRepository.findUserByUsername(username);

        validateLoginExistence(username, true);
        validatePassword(user, password);

        return user.getId();
    }

    public List<User> getAllUsers() throws ValidationException {
        List<User> users = userRepository.getAllUsers();

        if (users.isEmpty()) {
            throw new ValidationException("Нет пользователей в базе");
        }

        return users;
    }

    public void deleteByUserId(long userId) throws ValidationException {
        User user = userRepository.findUserById(userId);

        if (user != null) {
            userRepository.deleteByUserId(userId);
        } else {
            throw new ValidationException("Пользователь с таким ID не существует");
        }
    }

    private void validateLoginExistence(String username, boolean shouldExist) throws ValidationException {
        if (username == null || username.isBlank()) {
            throw new ValidationException("Логин не может быть пустым");
        }

        User user = userRepository.findUserByUsername(username);

        if (shouldExist && user == null) {
            throw new ValidationException("Пользователь с таким логином не найден");
        }

        if (!shouldExist && user != null) {
            throw new ValidationException("Этот логин уже используется кем-то");
        }
    }

    private void validatePassword(String password) throws ValidationException {
        if (password == null || password.isBlank()) {
            throw new ValidationException("Пароль не может быть пустым");
        }
    }

    public void validatePassword(User user, String password) throws ValidationException {
        boolean result = user.getPassword().equals(password);

        if (!result) {
            throw new ValidationException("Неправильный пароль");
        }
    }

    public void createTable() {
        userRepository.createTable();
    }
}