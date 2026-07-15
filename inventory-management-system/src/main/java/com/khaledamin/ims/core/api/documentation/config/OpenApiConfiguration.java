package com.khaledamin.ims.core.api.documentation.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        Server localServer = new Server()
                .url("http://localhost:8080/api/v1")
                .description("Local Development Environment");

        Server productionServer = new Server()
                .url("https://inventory.company.com/api/v1")
                .description("Production Environment");

        return new OpenAPI()

                .info(
                        new Info()
                                .title("Inventory Management System API")
                                .version("1.0.0")
                                .description("""
                                        Multi-Tenant Inventory Management Platform.

                                        IMS is the authoritative source of truth for inventory operations across the ecosystem.

                                        Core Responsibilities:
                                        - Inventory ownership
                                        - Stock management
                                        - Batch tracking
                                        - Inventory availability
                                        - Stock reservations
                                        - Reservation lifecycle management
                                        - Inventory allocation
                                        - Inventory integrations

                                        Supported Actors:
                                        - Account (Human Actor)
                                        - Client (Machine Actor)

                                        Authentication:
                                        - JWT Bearer Authentication

                                        Authorization:
                                        - Role-Based Access Control (RBAC)
                                        - Capability-Based Authorization
                                        - Scope-Based Machine Authorization

                                        Platform Features:
                                        - Multi-tenant organization isolation
                                        - Reservation expiration workflows
                                        - Auditing and actor attribution
                                        - Structured logging and observability
                                        - Verification and notification infrastructure

                                        Standard Response Contracts:
                                        - ApiResponse<T>
                                        - ApiPageResponse<T>
                                        - ApiErrorResponse
                                        """)
                                .contact(
                                        new Contact()
                                                .name("Khaled Amin")
                                                .email("khaledamin.dev@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("Portfolio Project")
                                )
                )

                .servers(
                        List.of(
                                localServer,
                                productionServer
                        )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication")
                )

                .components(new Components());
    }
}