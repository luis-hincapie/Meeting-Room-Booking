package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingRequestMapper bookingRequestMapper;

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookingRequest>> bookingList(@NotNull Principal principal) {
        return ResponseEntity.ok(bookingService
                .findBookingsByUsername(principal.getName())
                .stream()
                .map(booking -> bookingRequestMapper.toBookingRequest(booking))
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody CreateUserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findUsers() {
        return userService.findAll();
    }
}
