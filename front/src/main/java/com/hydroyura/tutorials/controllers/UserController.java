package com.hydroyura.tutorials.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.tutorials.config.CustomUserDetails;
import com.hydroyura.tutorials.models.events.UserChangeEmail;
import com.hydroyura.tutorials.producers.ByteArrProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired @Qualifier("userUpdateEmailByteArrProducer")
    private ByteArrProducer byteArrProducer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/update-email")
    public String updateEmail(Authentication authentication, @RequestParam String newEmail) throws JsonProcessingException {
        if (authentication.getPrincipal() instanceof CustomUserDetails user) {
            String id = user.getId();
            UserChangeEmail event = new UserChangeEmail()
                    .setId(id)
                    .setNewEmail(newEmail);
            byte[] bytes = objectMapper.writeValueAsBytes(event);
            byteArrProducer.send(bytes);
            return "/users/email-updated";
        } else {
            return "/error";
        }
    }

}
