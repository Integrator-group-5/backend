package com.luxury.wear.service.config;

import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoadAdmin implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        try {
            if (userRepository.findByEmail(adminEmail).isPresent()) {
                System.out.println("Admin with email " + adminEmail + " already exists.");
                return;
            }
            if (userRepository.findByUsername("admin").isPresent()) {
                System.out.println("Admin with username 'admin' already exists.");
                return;
            }

            String password = passwordEncoder.encode(adminPassword);
            User user = new User(1L, "admin", "admin", "admin", adminEmail, password, UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin user created successfully.");

        } catch (IllegalStateException e) {
            System.out.println("Error creating admin user: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred while creating admin user: " + e.getMessage());
        }
    }
}
