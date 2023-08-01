package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;

public interface ClientService {
    ClientRequest create(ClientRequest clientRequest);
    ClientRequest findById(Long id);
    List<ClientRequest> findAll();
    void delete(Long id);
    ClientRequest update(Client client);
    Client findClient(Long id);
}
