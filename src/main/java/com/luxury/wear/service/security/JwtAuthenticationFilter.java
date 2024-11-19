package com.luxury.wear.service.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // Map of endpoints to allowed methods without authentication
    private static final Map<String, List<String>> EXCLUDED_PATHS = Map.of(
            "/auth/**", List.of("POST"),
            "/api/v1/products/**", List.of("GET"),
            "/swagger-ui/**", List.of("GET"),
            "/v3/api-docs/**", List.of("GET"),
            "/api/v1/categories", List.of("GET"),
            "/api/v1/categories/{id}", List.of("GET"),
            "/api/v1/sizes", List.of("GET"),
            "/public/**", List.of("GET"),
            "/h2-console/**", List.of("GET", "POST", "PUT", "DELETE")
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        // Bypass OPTIONS requests and excluded paths
        if ("OPTIONS".equalsIgnoreCase(requestMethod) || isExcludedPath(requestURI, requestMethod)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = jwtUtil.getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtUtil.validateToken(jwt)) {
                String username = jwtUtil.getEmailFromToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            // Handle token expiration, e.g., by sending a custom response or allowing for token refresh
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has expired, please refresh your token.");
            return;
        } catch (JwtException e) {
            // Handle other token errors (e.g., signature validation failure)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token.");
            return;
        } catch (Exception e) {
            // Generic exception handling for unexpected errors
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred during token validation.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isExcludedPath(String requestURI, String requestMethod) {
        return EXCLUDED_PATHS.entrySet().stream()
                .anyMatch(entry -> pathMatcher.match(entry.getKey(), requestURI) && entry.getValue().contains(requestMethod));
    }
}
