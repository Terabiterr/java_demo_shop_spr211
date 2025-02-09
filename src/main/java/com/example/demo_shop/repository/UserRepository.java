package com.example.demo_shop.repository;

import com.example.demo_shop.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByUsername(String username);
}
