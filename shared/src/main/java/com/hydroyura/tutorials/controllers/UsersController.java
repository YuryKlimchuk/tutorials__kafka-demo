package com.hydroyura.tutorials.controllers;

import com.hydroyura.tutorials.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UsersController {

    @GetMapping(value = "/user/{name}")
    ResponseEntity<User> getUser(@PathVariable String name);

}
