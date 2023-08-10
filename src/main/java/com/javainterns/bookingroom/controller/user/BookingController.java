package com.javainterns.bookingroom.controller.user;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.jetbrains.annotations.ApiStatus.AvailableSince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.dto.TimeSlot;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Tag(name = "User Bookings")
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

  @Autowired
  BookingService bookingService;

  @Autowired
  BookingRequestMapper bookingRequestMapper;

  @Operation(summary = "Get all bookings of current user")
  @GetMapping
  public ResponseEntity<List<BookingRequest>> bookingList(
    @NotNull Principal principal
  ) {    
    return ResponseEntity.ok(
      bookingService
        .findBookingsByUsername(principal.getName())
        .stream()
        .map(booking -> bookingRequestMapper.toBookingRequest(booking))
        .toList()
    );
  }

  @Operation(summary = "Get a booking by ID for current user")
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
  public ResponseEntity<BookingRequest> getBooking(
    @PathVariable Long id,
    Principal principal
  ) {
    return new ResponseEntity<>(
      bookingService.findById(id, principal),
      HttpStatus.OK
    );
  }

  @Operation(summary = "Delete a booking by booking ID")
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
  public ResponseEntity<String> deleteBooking(
    @PathVariable Long id,
    Principal principal
  ) {
    bookingService.deleteByUser(id, principal);
    return new ResponseEntity<String>("Booking deleted", HttpStatus.OK);
  }

  @Operation(summary = "Create a booking for current user")
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
  @PostMapping
  public ResponseEntity<BookingRequest> createBooking(
    @Valid @RequestBody BookingRequest bookingRequest,
    Principal principal
  ) {
    return ResponseEntity.ok(bookingService.create(bookingRequest, principal));
  }

  @Operation(summary = "Get available time slots")
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Available time slots found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = TimeSlot.class)
        )
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Room not found",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
    }
  )
  @GetMapping("/available")
  @ApiResponse(
    responseCode = "200",
    description = "Available time slots found",
    content = @Content(
      mediaType = "application/json",
      schema = @Schema(implementation = TimeSlot.class)
    )
  )
  @AvailableSince("1.1.0")
  public List<TimeSlot> getAvailableTimeSlots(
    @RequestParam Long id,
    @RequestParam LocalDate date
  ) {
    return bookingService.findAvailableTimeSlots(id, date);
  }
}
