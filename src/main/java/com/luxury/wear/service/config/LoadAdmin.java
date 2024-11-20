package com.luxury.wear.service.config;

import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoadAdmin implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    public LoadAdmin(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (adminEmail == null || adminPassword == null) {
            log.error("Admin email or password is not configured properly.");
            return;
        }

        try {
            if (userRepository.findByEmail(adminEmail).isPresent()) {
                log.info("Admin with email {} already exists.", adminEmail);
                return;
            }

            String encodedPassword = passwordEncoder.encode(adminPassword);
            User user = new User(null, "admin", "admin", adminEmail, encodedPassword, UserRole.ADMIN, null);
            userRepository.save(user);
            log.info("Admin user created successfully.");

        } catch (IllegalStateException e) {
            log.error("Error creating admin user: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating admin user: {}", e.getMessage(), e);
        }
    }
}
