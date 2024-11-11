package com.luxury.wear.service.service.auth;

import com.luxury.wear.service.dto.auth.AuthResponse;
import com.luxury.wear.service.dto.auth.LoginRequest;
import com.luxury.wear.service.dto.auth.TokenRefreshRequest;
import com.luxury.wear.service.dto.user.UserRequestDto;
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
    public void registerUser(UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
    }

    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String email = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role not found"))
                .getAuthority().replace("ROLE_", ""); // Clean up the ROLE_ prefix

        String accessToken = jwtUtil.generateAccessToken(email, role);
        String refreshToken = jwtUtil.generateRefreshToken(email);

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
        String token = jwtUtil.getJwtFromRequest(request);
        if (token != null) {
            tokenBlacklistService.addTokenToBlacklist(token);
        }

    }
}
