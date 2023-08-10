package com.javainterns.bookingroom.controller.admin;

import com.javainterns.bookingroom.service.UserService;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin Users")
@RestController
@RequestMapping(path = "/users")
public class AdminUserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Operation(summary = "Get users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User list"),
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findUsers() {
        return userService.findAll();
    }
}
