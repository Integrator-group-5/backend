package com.luxury.wear.service.service.auth;

import com.luxury.wear.service.dto.auth.AuthResponse;
import com.luxury.wear.service.dto.auth.LoginRequest;
import com.luxury.wear.service.dto.auth.TokenRefreshRequest;
import com.luxury.wear.service.dto.user.UserRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void registerUser(UserRequestDto userRequestDto);

    AuthResponse authenticateUser(LoginRequest loginRequest);

    AuthResponse refreshAccessToken(TokenRefreshRequest tokenRefreshRequest);

    void logoutUser(HttpServletRequest request, HttpServletResponse response);
}
