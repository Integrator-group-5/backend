package com.luxury.wear.service.service.auth;

import com.luxury.wear.service.dto.auth.AuthResponse;
import com.luxury.wear.service.dto.auth.LoginRequest;
import com.luxury.wear.service.dto.auth.TokenRefreshRequest;
import com.luxury.wear.service.entity.User;

public interface AuthService {

    void registerUser(User user);

    AuthResponse authenticateUser(LoginRequest loginRequest);

    AuthResponse refreshAccessToken(TokenRefreshRequest tokenRefreshRequest);

    void logoutUser();
}
