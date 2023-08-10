package com.javainterns.bookingroom.controller.user;

import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Rooms")
@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

  @Autowired
  RoomService roomService;

  @Operation(summary = "Get all rooms")
  @ApiResponses(
    value = { @ApiResponse(responseCode = "200", description = "Users found") }
  )
  @GetMapping
  public ResponseEntity<List<RoomRequest>> findAll() {
    return ResponseEntity.ok(roomService.findAll());
  }

  @Operation(summary = "Get a room by ID")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "User found"),
      @ApiResponse(
        responseCode = "404",
        description = "User not found",
        content = @Content(schema = @Schema(implementation = Void.class))
      ),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<RoomRequest> findById(@PathVariable @NotNull Long id) {
    return ResponseEntity.ok(roomService.findById(id));
  }
}
