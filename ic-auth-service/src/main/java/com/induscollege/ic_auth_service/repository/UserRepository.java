package com.induscollege.ic_auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.induscollege.ic_auth_service.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);
    
}
