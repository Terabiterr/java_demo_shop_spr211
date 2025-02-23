package com.example.demo_shop.controller;

import com.example.demo_shop.model.User;
import com.example.demo_shop.repository.UserRepository;
import com.example.demo_shop.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public AuthController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername()) == null) {
            return ResponseEntity.badRequest().body("Username is already in use");
        } else {
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        if(!foundUser.isPresent() || !user.getPassword().equals(foundUser.get().getPassword())) {
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        } else {
            String token = jwtService.generateToken(foundUser.get().getUsername());
            return ResponseEntity.ok(token);
        }
    }

}
