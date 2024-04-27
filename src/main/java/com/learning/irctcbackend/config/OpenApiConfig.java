package com.learning.irctcbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "BookMangementSystem API",
        description = "CRUD operations api",
        summary = "This api has functionalities like add, delete, create, update, and search",
        termsOfService = "T&C",
        contact = @Contact(
            name = "lakshmi",
            email = "lakshmi@gmail.com"
        ),
        license = @License(
            name = "abcd"
        ),
        version = "v3"

    ),
    servers = {
        @Server(
            description = "Dev",
            url = "http://localhost:8080"
        ),
        @Server(
            description = "Test",
            url = "http://localhost:8080"
        )
    },
    security = @SecurityRequirement(
        name = "JWT"
    )
)

@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {

}