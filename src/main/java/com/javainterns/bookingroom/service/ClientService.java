package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;

import java.util.List;

public interface ClientService {
    ClientRequest create(ClientRequest clientRequest);
    ClientRequest findById(Long id);
    List<ClientRequest> findAll();
    void delete(Long id);
    ClientRequest update(ClientRequest clientRequest);
    Client finClient(Long id);
}
