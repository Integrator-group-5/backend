package com.luxury.wear.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        // Endpoints accessible without authentication
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow all OPTIONS requests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/v1/countries/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products", "/api/v1/products/{id}", "/api/v1/products/paginated", "/api/v1/products/top-rents", "api/v1/products/by-reference/{reference}", "api/v1/products/by-name/{name}", "/api/v1/products/{id}/availability").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories", "/api/v1/categories/{id}", "/api/v1/categories/paginated").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/sizes").permitAll()
                        .requestMatchers("/h2-console/**", "/v3/api-docs/**", "/webjars/swagger-ui/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/api/v1/addresses/{countryName}/pickup-sites").permitAll()

                        // Endpoints requiring authentication with USER or ADMIN roles
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/email", "/api/v1/users/{id}").hasAnyRole("USER", "ADMIN")

                        // Endpoints requiring authentication with ADMIN role
                        .requestMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/delete-product/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/users/paginated", "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/set-admin", "/api/v1/users/remove-admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/delete-user/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/delete-category/{id}").hasRole("ADMIN")

                        // All other requests
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

