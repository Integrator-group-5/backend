package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.EmailRequest;
import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto existingUser = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all users paginated with default page size of 6")
    public ResponseEntity<Page<UserResponseDto>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDto> users = userService.getAllUsersPaginated(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete a user by id")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("userId", id.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/email")
    @Operation(summary = "Get a user by email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestBody EmailRequest email) {
        UserResponseDto existingUser = userService.findByEmail(email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }

    @GetMapping("/user-info")
    @Operation(summary = "Get a user by token")
    public ResponseEntity<UserResponseDto> getUserByToken(Authentication authentication) {
        UserResponseDto user = userService.findByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/user-initials")
    @Operation(summary = "Get a user initials by token")
    public ResponseEntity<Map<String, String>> getUserInitialsByToken(Authentication authentication) {
        UserResponseDto user = userService.findByEmail(authentication.getName());
        Map<String, String> response = new HashMap<>();
        response.put("initials", user.getFirstName().charAt(0) + user.getLastName().substring(0, 1));
        response.put("first_name", user.getFirstName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/set-admin")
    @Operation(summary = "Set a user as an admin")
    public ResponseEntity<Map<String, String>> setAdmin(@RequestBody EmailRequest email) {
        userService.setAdmin(email.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User is now an admin");
        response.put("email", email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/remove-admin")
    @Operation(summary = "Remove a user as an admin")
    public ResponseEntity<Map<String, String>> removeAdmin(@RequestBody EmailRequest email) {
        userService.removeAdmin(email.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "User is no longer an admin");
        response.put("email", email.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
