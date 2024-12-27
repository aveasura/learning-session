package org.learning.session.service;

import org.learning.session.model.User;
import org.learning.session.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        return user;
    }

    public long createUser(String username, String password) throws ValidationException {

        // Проверяем логин на заполненность. || Потом можно добавить проверку на занятость логина
        if (username == null || username.isBlank()) {
            throw new ValidationException("Логин не может быть пустым");
        }

        // Проверка пароля
        if (password == null || password.isBlank()) {
            throw new ValidationException("Пароль не может быть пустым");
        }

        return userRepository.createUser(username, password);
    }

    public long authenticateUser(String username, String password) throws ValidationException {
        User user = findUserByUsername(username);

        isLoginExist(user);
        isPasswordValid(user, password);

        return user.getId();
    }

    private void isLoginExist(User user) throws ValidationException {
        boolean result = userRepository.isUserExist(user);

        if (!result) {
            throw new ValidationException("Неправильный логин");
        }
    }

    public void isPasswordValid(User user, String password) throws ValidationException {
        boolean result = user.getPassword().equals(password);

        if (!result) {
            throw new ValidationException("Неправильный пароль");
        }
    }

    public void deleteByUserId(long userId) throws ValidationException {

        User user = findUserById(userId);
        if (user != null) {
            userRepository.deleteByUserId(userId);
        } else {
            throw new ValidationException("Пользователь с таким ID не существует");
        }
    }
}