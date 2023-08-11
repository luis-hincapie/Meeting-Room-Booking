package com.javainterns.bookingroom.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .addSecurityItem(
        new SecurityRequirement().addList("Bearer Authentication")
      )
      .components(
        new Components()
          .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
      )
      .info(
        new Info()
          .title("Booking meeting rooms API")
          .description(
            "Meeting Room Booking System where users can book meeting rooms for certain time slots."
          )
          .version("1.0")
          .contact(
            new Contact()
              .name("Java Interns")
              .email(
                "david.gomez@blankfactor.com,david.medina@blankfactor.com,luis.hincapie@blankfactor.com"
              )
              .url("http://www.blankfactor.com")
          )
          .license(
            new io.swagger.v3.oas.models.info.License()
              .name("Apache 2.0")
              .url("http://www.apache.org/licenses/LICENSE-2.0.html")
          )
      );
  }

  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme()
      .type(SecurityScheme.Type.HTTP)
      .bearerFormat("JWT")
      .scheme("bearer");
  }
}
