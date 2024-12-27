package org.learning.session.service;

import org.learning.session.model.User;
import org.learning.session.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public long createUser(String username, String password) {
        return userRepository.createUser(username, password);
    }
}