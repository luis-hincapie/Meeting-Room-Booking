package com.javainterns.bookingroom.model.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;

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
        return new CreateUserDTO(
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
