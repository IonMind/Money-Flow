package com.induscollege.ic_auth_service.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.induscollege.ic_auth_service.models.User;
import com.induscollege.ic_auth_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTService jwtService;

    // @Autowired
    // PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.findBy(email);
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword()).build();
    }

    @Override
    public User findBy(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("No user with email: " + email));

    }

    @Override
    public Boolean validateJWTToken(String token) {
        final String userEmail = jwtService.extractUsername(token);
        User user = this.findBy(userEmail);
        if (!jwtService.isTokenValid(token, user)) {
            return false;
        }
        return jwtService.isTokenValid(token, user);
    }

    // @Override
    // public User authenticateUser(String email, String password) {

    // return this.findBy(email);
    // }

}
