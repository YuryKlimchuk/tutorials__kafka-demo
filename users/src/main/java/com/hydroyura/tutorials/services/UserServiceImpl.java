package com.hydroyura.tutorials.services;

import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.models.events.UserChangeEmail;
import com.hydroyura.tutorials.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;


    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.getUserById(id);
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepo.getUserByName(name);
    }

    @Override
    public Boolean updateEmail(UserChangeEmail userChangeEmail) {
        return userRepo.updateEmail(userChangeEmail.getId(), userChangeEmail.getNewEmail());
    }
}
