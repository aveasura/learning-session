package org.learning.session.dao;

import org.learning.session.model.User;

import java.util.List;

public interface UserDao {

    long create(String username, String password);

    List<User> readAll();
    User readByUsername(String username);
    User readById(long id);

    void update(long id);

    void delete(long id);
}
