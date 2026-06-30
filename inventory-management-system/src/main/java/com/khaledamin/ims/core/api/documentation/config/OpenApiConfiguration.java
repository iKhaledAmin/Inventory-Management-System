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
                                .version("v1")
                                .description("""
                                        Inventory Management System (IMS).

                                        The IMS is the authoritative source of truth for inventory operations.

                                        Responsibilities:
                                        - Inventory ownership
                                        - Stock management
                                        - Stock batches
                                        - Inventory availability
                                        - Stock reservations
                                        - Inventory integrations

                                        Supported Actors:
                                        - Account (human-actor)
                                        - Client  (non-human-actor)
                                         

                                        Authentication:
                                        JWT Bearer Token

                                        Authorization:
                                        - RBAC
                                        - Permission-Based Authorization for Human-Actor (Account)
                                        - Scopes Authorization for Machines (Client)

                                        Response Contracts:
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