package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Client;

public interface ClientService {
    Client create(Client client);
    Optional<Client> findById(Long id);

    List<Client> findAll();

    void delete(Long id);

    Client update(Client client);
}
