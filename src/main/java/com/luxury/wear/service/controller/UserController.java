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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("userId", id.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody EmailRequest email) {
        User existingUser = userService.findByEmail(email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @GetMapping("/user-info")
    public ResponseEntity<User> getUserByToken(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/user-initials")
    public ResponseEntity<Map<String, String>> getUserInitialsByToken(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        Map<String, String> response = new HashMap<>();
        response.put("initials", user.getFirstName().charAt(0) + user.getLastName().substring(0, 1));
        response.put("first_name", user.getFirstName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/set-admin")
    public ResponseEntity<Map<String, String>> setAdmin(@RequestBody EmailRequest email) {
        userService.setAdmin(email.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User is now an admin");
        response.put("email", email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/remove-admin")
    public ResponseEntity<Map<String, String>> removeAdmin(@RequestBody EmailRequest email) {
        userService.removeAdmin(email.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User is no longer an admin");
        response.put("email", email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
