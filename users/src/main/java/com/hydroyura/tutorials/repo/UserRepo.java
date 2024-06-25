package com.hydroyura.tutorials.repo;

import com.hydroyura.tutorials.models.User;

import java.util.Optional;

public interface UserRepo {

    Optional<User> getUserById(String id);
    Optional<User> getUserByName(String name);
    Boolean updateEmail(String id, String newEmail);

}
