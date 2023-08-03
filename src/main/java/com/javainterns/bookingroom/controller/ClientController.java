package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.dto.*;
import com.javainterns.bookingroom.service.ClientService;
import com.javainterns.bookingroom.service.ClientServiceImpl;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
    })
    @GetMapping
    public ResponseEntity<List<ClientRequest>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Operation(summary = "Get a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found",content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientRequest> findById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Operation(summary = "Get a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "404", description = "Client not found",content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping
    public ResponseEntity<ClientRequest> create(@Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.create(clientRequest));
    }
    
    @Operation(summary = "Create a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client updated"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content(schema = @Schema(implementation = Void.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientRequest> update(@Valid @RequestBody ClientRequest clientRequest, @PathVariable @NotNull Long id) {
        clientRequest.setId(id);
        return ResponseEntity.ok(clientService.update(clientRequest));
    }

    @Operation(summary = "Delete a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content(schema = @Schema(implementation = Void.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(clientService.authenticate(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(clientService.registerUser(signUpRequest));
    }

    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
    return ResponseEntity.ok(clientService.changePassword(changePasswordRequest));
    }
    

}
