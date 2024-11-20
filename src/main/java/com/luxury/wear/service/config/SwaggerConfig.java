package com.luxury.wear.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "Luxury Wear API",
                version = "1.0",
                description = "Endpoints for Luxury Wear API",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Grupo 5 Digital House",
                        url = "https://github.com/orgs/Integrator-group-5/repositories"
                )
        ),

        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local server"
                ),
                @Server(
                        url = "https://luxury-wear.pendiente.com",
                        description = "Production server"
                )
        },
        security = {
                @SecurityRequirement(name = "Security token")
        }

)
@SecurityScheme(
        name = "Security token",
        description = "JWT authentication",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
