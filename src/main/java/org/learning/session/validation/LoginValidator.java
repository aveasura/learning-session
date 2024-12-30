package org.learning.session.validation;

import java.util.Map;

public interface LoginValidator {
    Map<String, String> validate(String username, String password);
}