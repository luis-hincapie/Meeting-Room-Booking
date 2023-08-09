package com.javainterns.bookingroom.controller.admin;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class AdminUserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findUsers() {
        return userService.findAll();
    }
}
