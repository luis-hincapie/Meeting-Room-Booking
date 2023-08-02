package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;
import com.javainterns.bookingroom.service.ClientService;
import com.javainterns.bookingroom.service.ClientServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private ClientService clientService;
    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientRequest>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRequest> findById(
            @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientRequest> create(@Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.create(clientRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRequest> update(@Valid @RequestBody ClientRequest clientRequest, @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(clientService.update(clientRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
