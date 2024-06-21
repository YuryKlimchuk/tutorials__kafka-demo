package com.hydroyura.tutorials.services;

import com.hydroyura.tutorials.models.User;
import com.hydroyura.tutorials.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user) {
        Boolean isCreated = userRepo.create(user);
        LOG.info("Creating user = [{}], result = [{}]", user, isCreated);

        if (isCreated) {

        } else {

        }
    }


}
