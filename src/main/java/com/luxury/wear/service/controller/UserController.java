package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.EmailRequest;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User existingUser = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<User>> getAllUsersPaginated(@PathVariable int page) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody EmailRequest email) {
        User existingUser = userService.findByEmail(email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @PutMapping("/set-admin")
    public ResponseEntity<String> setAdmin(@RequestBody EmailRequest email) {
        userService.setAdmin(email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body("User with email: " + email.getEmail() + " is now an admin");
    }
}
