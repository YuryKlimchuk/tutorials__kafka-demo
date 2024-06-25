package com.hydroyura.tutorials.config;

import com.hydroyura.tutorials.clients.UsersClient;
import com.hydroyura.tutorials.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUsersDetailService implements UserDetailsService {

    @Autowired
    private UsersClient usersClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<User> response = usersClient.getUser(username);
        CustomUserDetails user = new CustomUserDetails()
                .setUsername(username)
                .setId(response.getBody().getId())
                .setAuthorities(Collections.emptySet())
                .setEmail(response.getBody().getEmail())
                .setPassword(response.getBody().getPassword());
        return user;
    }
}
