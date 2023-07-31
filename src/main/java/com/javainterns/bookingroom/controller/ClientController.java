package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.service.ClientService;
import com.javainterns.bookingroom.service.ClientServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class ClientController {
    @Autowired
    private ClientService clientService;

    public ClientController(ClientServiceImpl userService) {
        this.clientService = userService;
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client CLient) {
        Client ClientCreate = clientService.create(CLient);
        return new ResponseEntity<>(ClientCreate, null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody Client CLient, @PathVariable @NotNull Long id) {
        CLient.setId(id);
        return ResponseEntity.ok(clientService.update(CLient));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> findById(
            @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
