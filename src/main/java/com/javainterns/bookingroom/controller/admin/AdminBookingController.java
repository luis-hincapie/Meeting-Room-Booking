package com.javainterns.bookingroom.controller.admin;

import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/bookings")
public class AdminBookingController {
    @Autowired
    BookingService bookingService;

    @Operation(summary = "Get all bookings")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Booking found"),
            }
    )
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookingRequest>> bookingList() {
        return ResponseEntity.ok(bookingService.findAll());
    }


}
