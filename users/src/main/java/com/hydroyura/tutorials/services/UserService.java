package com.hydroyura.tutorials.services;

import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.models.events.UserChangeEmail;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(String id);
    Optional<User> getUserByName(String name);
    Boolean updateEmail(UserChangeEmail userChangeEmail);

}
