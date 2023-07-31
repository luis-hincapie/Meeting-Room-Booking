package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client create(Client CLient);
    Optional<Client> findById(Long id);

    List<Client> findAll();

    void delete(Long id);

    Client update(Client CLient);
}
