package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(CreateUserDTO userRequest) {
        return new User(null,
                userRequest.getEmail(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                null,
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
