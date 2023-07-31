package com.javainterns.bookingroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.ClientService;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<BookingRequest>> bookingList(){
        return ResponseEntity.ok(bookingService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.delete(id);
        return new ResponseEntity<String>("Booking deleted",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingRequest> getBooking(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BookingRequest> createBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.create(bookingRequest));
    }
}
