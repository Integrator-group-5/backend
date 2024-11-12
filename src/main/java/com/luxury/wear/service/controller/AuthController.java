package com.luxury.wear.service.controller;

import com.luxury.wear.service.dto.auth.AuthResponse;
import com.luxury.wear.service.dto.auth.LoginRequest;
import com.luxury.wear.service.dto.auth.TokenRefreshRequest;
import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.security.JwtUtil;
import com.luxury.wear.service.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        authService.registerUser(userRequestDto);
        Map<String, String> response = new HashMap<>();
        response.put("response", "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @PostMapping("/token-refresh")
    // todo should the token come in the requestbody or would be better in the Authorization Header?
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {

        AuthResponse refreshAccessTokenResponse = authService.refreshAccessToken(tokenRefreshRequest);
        return ResponseEntity.status(HttpStatus.OK).body(refreshAccessTokenResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        authService.logoutUser(request, response);
        Map<String, String> apiResponse = new HashMap<>();
        apiResponse.put("response", "User logged out successfully");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
