package com.hydroyura.tutorials.repo;

import com.hydroyura.tutorials.models.User;

import java.util.Optional;

public interface UserRepo {

    Optional<User> getUserById(String id);
    Optional<User> getUserByEmail(String email);
    Boolean create(User user);
    Boolean update(User user);
    Boolean updateEmail(String id, String newEmail);
    Boolean updateName(String id, String newName);

}
