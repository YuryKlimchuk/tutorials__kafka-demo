package com.hydroyura.tutorials.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.producers.UserCreateProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserCreateProducer userCreateProducer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/create-new")
    public String create() throws JsonProcessingException {
        User user = new User()
                .setEmail("email")
                .setId(UUID.randomUUID().toString())
                .setName("Name")
                .setSurname("Surname");




        userCreateProducer.sendMsg(objectMapper.writeValueAsString(user));

        return "/users/new";
    }

    @PostMapping(value = "/create-new")
    public String handleCreation(@ModelAttribute User newUser) {
        if (validateUser(newUser)) {

            return "111";
        } else {
            return "";
        }
    }

    @GetMapping(value = "/create-new/result")
    public String showCreationResult() {
        return "";
    }

    private Boolean validateUser(User user) {
        return Boolean.TRUE;
    }

}
