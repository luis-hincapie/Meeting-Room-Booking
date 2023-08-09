package com.javainterns.bookingroom.controller.user;

import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

  @Autowired
  BookingService bookingService;

  @Autowired
  BookingRequestMapper bookingRequestMapper;

  @GetMapping
  public ResponseEntity<List<BookingRequest>> bookingList(@NotNull Principal principal) {
    return ResponseEntity.ok(bookingService
            .findBookingsByUsername(principal.getName())
            .stream()
            .map(booking -> bookingRequestMapper.toBookingRequest(booking))
            .toList()
    );
  }

  @Operation(summary = "Get a booking by ID")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "Booking found"),
      @ApiResponse(
        responseCode = "404",
        description = "Booking not found",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<BookingRequest> getBooking(@PathVariable Long id) {
    return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
  }

  @Operation(summary = "Delete an booking by ID")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "Booking deleted"),
      @ApiResponse(
        responseCode = "404",
        description = "Booking not found",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
    }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
    bookingService.delete(id);
    return new ResponseEntity<String>("Booking deleted", HttpStatus.OK);
  }

  @Operation(summary = "Create an user")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "Booking created"),
      @ApiResponse(
        responseCode = "404",
        description = "User not found\t\nRoom not found",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Start time must not be greater than end time",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
      @ApiResponse(
        responseCode = "409",
        description = "Room booked\t\nRoom won't be open at this time",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
    }
  )
  @PostMapping("/")
  public ResponseEntity<BookingRequest> createBooking(
    @Valid @RequestBody BookingRequest bookingRequest
  ) {
    return ResponseEntity.ok(bookingService.create(bookingRequest));
  }
}
