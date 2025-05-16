package com.dummy.dummy_endpoints.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .components(new Components()
                            .addSecuritySchemes("Bearer Authentication",
                                    new SecurityScheme()
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                            )
                    )
                    .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                    .info(new Info()
                            .title("Dummy Endpoints API")
                            .description("Dummy Endpoints API documentation for Hammad")
                            .version("1.0.0")
                            .contact(new Contact()
                                    .name("DevAxiom")
                                    .url("https://www.funnelskingdom.com/")
                                    .email("huzaifanaseer084@gmail.com")
                            )
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                            )
                    );
        }
}