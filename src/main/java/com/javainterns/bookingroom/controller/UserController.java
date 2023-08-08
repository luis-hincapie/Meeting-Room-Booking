package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.service.BookingService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  BookingService bookingService;

  @GetMapping("/{id}/bookings")
  public ResponseEntity<List<BookingRequest>> bookingList(
    @PathVariable Long id,
    Principal principal
  ) {
    return ResponseEntity.ok(bookingService.findAll());
  }
}
