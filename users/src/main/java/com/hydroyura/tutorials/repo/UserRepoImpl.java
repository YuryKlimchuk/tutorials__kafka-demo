package com.hydroyura.tutorials.repo;

import com.hydroyura.tutorials.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepoImpl implements UserRepo {

    private final Map<String, User> storage = new HashMap<>();


    public UserRepoImpl() {
        String UUID1 = java.util.UUID.randomUUID().toString();
        storage.put(UUID1, new User()
                .setEmail("user1@gmail.com")
                .setName("Name1")
                .setSurname("Surname1")
                .setId(UUID1)
        );

        String UUID2 = java.util.UUID.randomUUID().toString();
        storage.put(UUID2, new User()
                .setEmail("user2@gmail.com")
                .setName("Name2")
                .setSurname("Surname2")
                .setId(UUID1)
        );
    }


    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Boolean create(User user) {
        String id = user.getId();
        String email = user.getEmail();

        Boolean isIdExist = storage.keySet().contains(id);
        Boolean isEmailExist = storage.values().stream().map(User::getEmail).anyMatch(e -> email.equals(e));

        if (isIdExist || isEmailExist) {
            return Boolean.FALSE;
        }

        storage.put(id, user);
        return Boolean.TRUE;
    }

    @Override
    public Boolean update(User user) {
        return null;
    }

    @Override
    public Boolean updateEmail(String id, String newEmail) {
        return null;
    }

    @Override
    public Boolean updateName(String id, String newName) {
        return null;
    }

}
