package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.dto.CreateUserDTO;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<List<BookingRequest>> bookingList(Principal principal) {
    return ResponseEntity.ok(
      bookingService
        .findBookingsByUsername(principal.getName())
        .stream()
        .map(booking -> bookingRequestMapper.toBookingRequest(booking))
        .toList()
    );
  }

  @PostMapping
  public ResponseEntity<User> create(
    @Valid @RequestBody CreateUserDTO userDTO
  ) {
    User user = userMapper.toUser(userDTO);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(userService.create(user));
  }

  @GetMapping
  public List<User> findUsers() {
    return userService.findAll();
  }
}
