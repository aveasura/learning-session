package org.learning.session.service;

import org.learning.session.model.User;
import org.learning.session.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long createUserAndGetId(String username, String password) throws ValidationException {

        isValidLogin(username);
        validateLoginExistence(username, false);
        isPasswordValid(password);

        return userRepository.createUser(username, password);
    }

    public long authenticateUser(String username, String password) throws ValidationException {
        User user = userRepository.findUserByUsername(username);

        validateLoginExistence(username, true);
        isPasswordValid(user, password);

        return user.getId();
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("пустой");
            throw new RuntimeException("Список пуст");
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
        User user = userRepository.findUserByUsername(username);

        if (shouldExist && user == null) {
            throw new ValidationException("Пользователь с таким логином не найден");
        }
        if (!shouldExist && user != null) {
            throw new ValidationException("Этот логин уже используется кем-то");
        }
    }

    private void isValidLogin(String username) throws ValidationException {
        if (username == null || username.isBlank()) {
            throw new ValidationException("Логин не может быть пустым");
        }
    }

    private void isPasswordValid(String password) throws ValidationException {
        if (password == null || password.isBlank()) {
            throw new ValidationException("Пароль не может быть пустым");
        }
    }

    public void isPasswordValid(User user, String password) throws ValidationException {
        boolean result = user.getPassword().equals(password);

        if (!result) {
            throw new ValidationException("Неправильный пароль");
        }
    }
}