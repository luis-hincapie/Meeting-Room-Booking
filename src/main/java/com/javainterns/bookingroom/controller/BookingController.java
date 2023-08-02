package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.service.BookingService;
import com.javainterns.bookingroom.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    ClientService clientService;

    @Operation(summary = "Get all bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found"),
    })
    @GetMapping("/")
    public ResponseEntity<List<BookingRequest>> bookingList(){
        return ResponseEntity.ok(bookingService.findAll());
    }

    @Operation(summary = "Delete an booking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking deleted"),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content(schema = @Schema(implementation = Void.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.delete(id);
        return new ResponseEntity<String>("Booking deleted",HttpStatus.OK);
    }

    @Operation(summary = "Get a booking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found"),
            @ApiResponse(responseCode = "404", description = "Booking not found",content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingRequest> getBooking(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.findById(id),HttpStatus.OK);
    }

    @Operation(summary = "Create an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking created"),
            @ApiResponse(responseCode = "404", description = "User not found\t\nRoom not found", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Start time must not be greater than end time", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Room booked\t\nRoom won't be open at this time", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping("/")
    public ResponseEntity<BookingRequest> createBooking(@Valid @RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.create(bookingRequest));
    }
}
