package com.example.collegedirectory.config;

import com.example.collegedirectory.model.User;
import com.example.collegedirectory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitialiser implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Check if the user already exists
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRole(User.Role.ADMINISTRATOR);
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
            System.out.println("Admin user created");
        }
    }
}