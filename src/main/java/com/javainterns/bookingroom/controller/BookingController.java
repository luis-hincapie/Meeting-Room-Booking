package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> bookingList(){
        return ResponseEntity.ok(bookingService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        return bookingService.delete(id) ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id){
        var queryResult = bookingService.findById(id);
        return queryResult.isPresent() ? new ResponseEntity<>(queryResult.get(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable Long id){
        return ResponseEntity.ok(bookingService.update(booking));
    }

    @PostMapping("/create")
    public ResponseEntity createBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.create(bookingRequest));
    }
}
