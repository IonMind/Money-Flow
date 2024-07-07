package com.induscollege.ic_auth_service.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.induscollege.ic_auth_service.models.User;

public interface UserService extends UserDetailsService { 
    public User saveUser(User user);
    // public User authenticateUser(String email,String password);
    public User findBy(String email);
    public Boolean validateJWTToken(String token);
}
