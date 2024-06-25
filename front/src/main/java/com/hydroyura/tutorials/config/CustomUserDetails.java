package com.hydroyura.tutorials.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private Set<GrantedAuthority> authorities = new HashSet<>();

    private String password, username, email, id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public CustomUserDetails setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public CustomUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomUserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getId() {
        return id;
    }

    public CustomUserDetails setId(String id) {
        this.id = id;
        return this;
    }
}
