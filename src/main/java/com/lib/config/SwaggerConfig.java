package com.lib.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI myCustomConfig() {

        return new OpenAPI()
                .info(
                    new Info()
                        .title("LibraCore APP APIs")
                        .description("Library Management System APIs")
                )

                .servers(
                    List.of(
                        new Server()
                            .url("http://localhost:8080")
                            .description("Local Server")
                    )
                )

                // JWT Security
                .components(
                    new Components()
                        .addSecuritySchemes(
                            "bearerAuth",
                            new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )

                .addSecurityItem(
                    new SecurityRequirement()
                        .addList("bearerAuth")
                );
    }
}