package com.hydroyura.tutorials.repo;

import com.hydroyura.tutorials.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class UserRepoImpl implements UserRepo {

    private final Map<String, User> storage = new HashMap<>();


    public UserRepoImpl() {
        storage.put("UUID1", new User()
                .setEmail("user1@gmail.com")
                .setName("Name1")
                .setSurname("Surname1")
                .setPassword("pwd")
                .setId("UUID1")
        );

        storage.put("UUID2", new User()
                .setEmail("user2@gmail.com")
                .setName("Name2")
                .setSurname("Surname2")
                .setPassword("pwd")
                .setId("UUID2")
        );
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return storage.values()
                .stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    /*
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
    */

    @Override
    public Boolean updateEmail(String id, String newEmail) {
        return getUserById(id)
                .map(user -> {
                    user.setEmail(newEmail);
                    storage.put(user.getId(), user);
                    return Boolean.TRUE;
                })
                .isPresent();
    }

    /*
    @Override
    public Boolean updateName(String id, String newName) {
        return getUserById(id)
                .map(user -> {
                    user.setName(newName);
                    storage.put(user.getId(), user);
                    return Boolean.TRUE;
                })
                .isPresent();
    }
    */
}
