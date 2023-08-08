package com.javainterns.bookingroom;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class BookingroomApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookingroomApplication.class, args);
  }

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Bean
  CommandLineRunner init() {
    return args -> {
      User userEntity = User
        .builder()
        .email("santiago@mail.com")
        .username("santiago")
        .password(passwordEncoder.encode("1234"))
        .roles(
          Set.of(Role.builder().name(ERole.valueOf(ERole.ADMIN.name())).build())
        )
        .build();

      User userEntity2 = User
        .builder()
        .email("anyi@mail.com")
        .username("anyi")
        .password(passwordEncoder.encode("1234"))
        .roles(
          Set.of(Role.builder().name(ERole.valueOf(ERole.USER.name())).build())
        )
        .build();

      User userEntity3 = User
        .builder()
        .email("andrea@mail.com")
        .username("andrea")
        .password(passwordEncoder.encode("1234"))
        .roles(
          Set.of(Role.builder().name(ERole.valueOf(ERole.USER.name())).build())
        )
        .build();

      userRepository.save(userEntity);
      userRepository.save(userEntity2);
      userRepository.save(userEntity3);
    };
  }

  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme()
      .type(SecurityScheme.Type.HTTP)
      .bearerFormat("JWT")
      .scheme("bearer");
  }

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
          .title("My REST API")
          .description("Some custom description of API.")
          .version("1.0")
          .contact(
            new Contact()
              .name("Sallo Szrajbman")
              .email("www.baeldung.com")
              .url("salloszraj@gmail.com")
          )
          .license(new License().name("License of API").url("API license URL"))
      );
  }
}
