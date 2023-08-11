package com.javainterns.bookingroom.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Tag(name = "User")
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Operation(summary = "Create a new user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
            }
    )
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    
    @Operation(summary = "Get current username")
    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(@NotNull Principal principal) {
        return principal.getName();
    }
}
