package com.hydroyura.tutorials.controller;

import com.hydroyura.tutorials.controllers.UsersController;
import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController implements UsersController {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<User> getUser(String name) {
        Optional<User> user = userRepo.getUserByName(name);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }
}
