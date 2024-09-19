package com.teamx.retroStore.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${apps.teamx.retroStore.openapi.security.schema.name}")
    private String securitySchemeName;

    @Value("${apps.teamx.retroStore.openapi.info.title}")
    private String title;

    @Value("${apps.teamx.retroStore.openapi.info.description}")
    private String description;

    @Value("${apps.teamx.retroStore.openapi.security.schema}")
    private String scheme;

    @Value("${apps.teamx.retroStore.openapi.security.bearer.format}")
    private String bearerFormat;

    @Value("${apps.teamx.retroStore.openapi.info.version}")
    private String version;
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title(this.title)
                        .description(this.description)
                        .version(this.version))
                .addSecurityItem(new SecurityRequirement()
                        .addList(this.securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(this.securitySchemeName, new SecurityScheme()
                                .name(this.securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme(this.scheme)
                                .bearerFormat(this.bearerFormat))
                );
    }
}
