package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toUser(CreateUserDTO userRequest) {
        return new User(null,
                userRequest.getEmail(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                Set.of(Role.builder().name(ERole.valueOf(ERole.USER.name())).build()),
                null

        );
    }

    public CreateUserDTO toUserRequest(User user) {
        return new CreateUserDTO( user.getEmail(), user.getUsername(), user.getPassword(), user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }
}
