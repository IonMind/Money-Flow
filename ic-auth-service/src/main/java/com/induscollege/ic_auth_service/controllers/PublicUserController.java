package com.induscollege.ic_auth_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.induscollege.ic_auth_service.models.LoginRequest;
import com.induscollege.ic_auth_service.models.User;
import com.induscollege.ic_auth_service.models.UserResponse;
import com.induscollege.ic_auth_service.services.JWTService;
import com.induscollege.ic_auth_service.services.UserService;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
        var u = userService.saveUser(user);

        return ResponseEntity.status(200).body(UserResponse.builder().name(u.getName()).email(u.getEmail()).build());
    }

    @GetMapping("/login")  
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        if (authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()))
                .isAuthenticated()) {

            User authenticatedUser = userService.findBy(loginRequest.email());
            String jwtToken = jwtService.generateToken(Collections.singletonMap("userId", authenticatedUser.getId()),authenticatedUser);
            return ResponseEntity.ok().body(UserResponse.builder().email(authenticatedUser.getEmail()).token(jwtToken)
                    .name(authenticatedUser.getName()).build());
        }
        return ResponseEntity.of(Optional.empty());

    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(userService.validateJWTToken(token));
    }

}
