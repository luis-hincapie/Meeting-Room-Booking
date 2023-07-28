package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;

    @GetMapping("/booking/all")
    public ResponseEntity<List<Booking>> bookingList(){
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/booking/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        return bookingService.delete(id) ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.findById(id));
    }

    @PutMapping("booking/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable Long id){
        return ResponseEntity.ok(bookingService.update(booking));
    }

    @GetMapping("/booking/create")
    public Booking createBooking(){
        User user = new User(

        );
        user.setName("David");
        user.setEmail("david@hola.com");
        userService.resgistration(user);
        Booking booking = new Booking();
        booking.setDate(LocalDate.now());
        booking.setUser(user);
        booking.setStartTime(LocalTime.of(12, 0));
        booking.setEndTime(LocalTime.of(14,0));
        bookingService.create(booking);
        return booking;
    }
}
