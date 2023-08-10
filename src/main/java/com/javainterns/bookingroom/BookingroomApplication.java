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
import com.javainterns.bookingroom.repository.RoleRepository;
import com.javainterns.bookingroom.repository.UserRepository;

@SpringBootApplication
public class BookingroomApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookingroomApplication.class, args);
  }

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Bean
  CommandLineRunner init() {


    return args -> {


      Role roleAdmin = Role.builder().name(ERole.valueOf(ERole.ADMIN.name())).build();
      Role roleUser = Role.builder().name(ERole.valueOf(ERole.USER.name())).build();

      roleRepository.save(roleAdmin);
      roleRepository.save(roleUser);

      User userEntity = User
        .builder()
        .email("santiago@mail.com")
        .username("santiago")
        .password(passwordEncoder.encode("1234"))
        .roles(
          Set.of(roleAdmin)
        )
        .build();

      User userEntity2 = User
        .builder()
        .email("anyi@mail.com")
        .username("anyi")
        .password(passwordEncoder.encode("1234"))
        .roles(
          Set.of(roleUser)
        )
        .build();

      userRepository.save(userEntity);
      userRepository.save(userEntity2);
    };
  }
}
