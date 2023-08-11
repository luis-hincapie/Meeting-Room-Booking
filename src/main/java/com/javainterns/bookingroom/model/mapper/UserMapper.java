package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserDto userRequest) {
        return new User(null,
                userRequest.getEmail(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                null,
                null

        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );
    }
}
