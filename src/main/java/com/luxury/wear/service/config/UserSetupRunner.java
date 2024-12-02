package com.luxury.wear.service.config;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
@Slf4j
public class UserSetupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.default.id:16}")
    private Long adminDefaultId;

    @Value("${default.user.range.start:1}")
    private Long defaultUserRangeStart;

    @Value("${default.user.range.end:10}")
    private Long defaultUserRangeEnd;

    public UserSetupRunner(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createOrUpdateAdmin();
        updateDefaultUserPasswords();
    }

    private void createOrUpdateAdmin() {
        if (adminEmail == null || adminPassword == null || adminEmail.isEmpty() || adminPassword.isEmpty()) {
            log.error("Admin email or password is not configured properly.");
            return;
        }

        if (!adminEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            log.error("Admin email is not a valid email address.");
            return;
        }

        // Check if the provided adminEmail is already in use by a different user
        userRepository.findByEmail(adminEmail).ifPresent(existingUser -> {
            if (!existingUser.getUserId().equals(adminDefaultId)) {
                log.error("The provided admin email {} is already in use by another user with ID {}.",
                        adminEmail, existingUser.getUserId());
                throw new EntityAlreadyExistsException(Constants.ERROR_EMAIL_ALREADY_EXISTS + adminEmail);
            }
        });

        User adminUser;
        if (userRepository.findById(adminDefaultId).isPresent()) {
            // Update existing user with ID 16L
            adminUser = userRepository.findById(adminDefaultId).get();
            log.info("User with ID {} found, updating to admin.", adminDefaultId);
        } else {
            // Create a new user if ID 16L does not exist
            adminUser = new User();
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Luxury Wear");
            log.info("User with ID {} not found, creating new admin user.", adminDefaultId);
        }

        // Set admin data
        adminUser.setEmail(adminEmail);
        adminUser.setPassword(passwordEncoder.encode(adminPassword));
        adminUser.setUserRole(UserRole.ADMIN);
        userRepository.save(adminUser);

        log.info("Admin user with ID {} successfully created/updated.", adminDefaultId);
    }

    private void updateDefaultUserPasswords() {
        try {
            List<User> users = userRepository.findAllById(
                    LongStream.rangeClosed(defaultUserRangeStart, defaultUserRangeEnd)
                            .boxed()
                            .collect(Collectors.toList())
            );

            users.forEach(user -> {
                user.setPassword(passwordEncoder.encode(user.getFirstName().toLowerCase() + 1));
            });

            userRepository.saveAll(users);
            log.info("Default users loaded with encoded passwords.");
        } catch (ResourceNotFoundException e) {
            log.error("Resource not found during default user update: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error during default user password update: {}", e.getMessage(), e);
        }
    }
}
