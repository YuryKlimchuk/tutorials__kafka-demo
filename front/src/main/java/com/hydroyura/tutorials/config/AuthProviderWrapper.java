package com.hydroyura.tutorials.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthProviderWrapper implements AuthenticationProvider {

    private AuthenticationProvider provider;

    public AuthProviderWrapper(AuthenticationProvider parent) {
        this.provider = parent;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return provider.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return provider.supports(authentication);
    }
}
