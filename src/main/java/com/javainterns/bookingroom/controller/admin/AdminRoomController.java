package com.javainterns.bookingroom.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Admin Rooms")
@RestController
@RequestMapping(path = "/admin/rooms")
public class AdminRoomController {

    private final RoomService roomService;

    @Autowired
    public AdminRoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @Operation(summary = "Create a room")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Room created"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Room not found",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Start time must not be greater than end time",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomRequest> create(@Valid @RequestBody RoomRequest roomRequest) {
        return ResponseEntity.ok(roomService.create(roomRequest));
    }

    @Operation(summary = "Update a room")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Room updated"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Room not found",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Start time must not be greater than end time",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
            }
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomRequest> update(@Valid @RequestBody RoomRequest roomRequest,
                                              @PathVariable @NotNull Long id) {
        roomRequest.setId(id);
        return ResponseEntity.ok(roomService.update(roomRequest));
    }

    @Operation(summary = "Delete a room by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Room deleted"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Room not found",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
            }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
