package com.cnsbd.aptitudo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080/aptitudo/");
        devServer.setDescription("Development environment");

        // Server prodServer = new Server();
        // prodServer.setUrl("http://localhost:9005/cpa-pmis/");
        // prodServer.setDescription("Production environment");

        Contact contact = new Contact();
        contact.setName("BISD (Java) Team");
        contact.setUrl("https://www.example.com");
        contact.setEmail("api.docs@co.cnsbd.com");

        License mitLicense = new License().name("MIT License").url("https://www.example.com/licenses/mit/");

        Info info = new Info()
                .title("BISD (Java)")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to manage BISD project (Java).")
                .termsOfService("https://www.example.com/terms")
                .license(mitLicense);

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components)
                .servers(List.of(devServer));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
