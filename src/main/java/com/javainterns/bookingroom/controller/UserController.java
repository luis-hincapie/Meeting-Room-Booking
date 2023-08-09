package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(@NotNull Principal principal) {
        return principal.getName();
    }
}
