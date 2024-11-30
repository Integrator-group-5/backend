package com.luxury.wear.service.config;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.repository.ReservationRepository;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import com.luxury.wear.service.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoadAdmin implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    public LoadAdmin(UserRepository userRepository, ProductRepository productRepository, ReservationRepository reservationRepository, ProductService productService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
        this.productService = productService;
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

/*            User adminUser = new User(
                    null,
                    "admin",
                    "admin",
                    "123456789",
                    "321654987",
                    adminEmail,
                    encodedPassword,
                    UserRole.ADMIN,
                    new ArrayList<>(),
                    new ArrayList<>()
            );*/

/*
            Product product = productRepository.findById(1L)
                    .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND_ID + 1L));
                    */

            User adminUser = userRepository.findById(16L)
                    .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + 16L));

            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setUserRole(UserRole.ADMIN);

            User karenPerez = userRepository.findById(11L)
                    .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + 11L));

            karenPerez.setPassword(passwordEncoder.encode("karen1"));

            User davidBlanco = userRepository.findById(7L)
                    .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + 7L));

            davidBlanco.setPassword(passwordEncoder.encode("david1"));

            userRepository.save(davidBlanco);
            userRepository.save(karenPerez);
            userRepository.save(adminUser);

            log.info("Admin user created successfully.");
        } catch (IllegalStateException e) {
            log.error("Error creating admin user: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while creating admin user: {}", e.getMessage(), e);
        }
    }
}
