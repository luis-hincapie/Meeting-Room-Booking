package com.javainterns.bookingroom.controller;

import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.dto.ClientRequest;
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

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.service.ClientService;
import com.javainterns.bookingroom.service.ClientServiceImpl;

import jakarta.validation.constraints.NotNull;

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
    public ResponseEntity<ClientRequest> create(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.create(clientRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRequest> update(@RequestBody Client client, @PathVariable @NotNull Long id) {
        client.setId(id);
        return ResponseEntity.ok(clientService.update(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
