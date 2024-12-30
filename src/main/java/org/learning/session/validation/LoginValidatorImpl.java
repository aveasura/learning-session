package org.learning.session.validation;

import java.util.HashMap;
import java.util.Map;

// Возможно буду использовать в дальнейшем.
public class LoginValidatorImpl implements LoginValidator{

    @Override
    public Map<String, String> validate(String username, String password) {
        Map<String, String> errors = new HashMap<>();

        if (username == null || username.isBlank()) {
            errors.put("username", "Логин не может быть пустым!");
        }
        if (password == null || password.isBlank()) {
            errors.put("password", "Пароль не может быть пустым!");
        }

        return errors;
    }
}