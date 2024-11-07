package com.luxury.wear.service.service.auth;

import com.luxury.wear.service.dto.auth.AuthResponse;
import com.luxury.wear.service.dto.auth.LoginRequest;
import com.luxury.wear.service.dto.auth.TokenRefreshRequest;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.security.JwtUtil;
import com.luxury.wear.service.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final TokenBlacklistServiceImpl tokenBlacklistService;

    @Override
    public void registerUser(User user) {
        userService.createUser(user);
    }

    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user.getEmail(), user.getUserRole().name());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refreshAccessToken(TokenRefreshRequest tokenRefreshRequest) {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        if (jwtUtil.validateToken(refreshToken)) {
            String email = jwtUtil.getEmailFromToken(refreshToken);
            String newAccessToken = jwtUtil.generateAccessToken(email, userService.findByEmail(email).getUserRole().name());
            return new AuthResponse(newAccessToken, refreshToken);
        } else {
            throw new RuntimeException("Invalid refresh token"); // todo create a custom exception for this case
        }
    }

    @Override
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        String token = jwtUtil.extractTokenFromRequest(request);
        if (token != null) {
            tokenBlacklistService.addTokenToBlacklist(token);
        }

    }
}
