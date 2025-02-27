package com.example.demo_shop.security;

import com.example.demo_shop.model.Role;
import com.example.demo_shop.model.User;
import com.example.demo_shop.repository.RoleRepository;
import com.example.demo_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setEmail("admin@gmail.com");
            user.setRoles(Set.of(role));
            userRepository.save(user);
        }
    }
}
