package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;

import java.util.List;

public interface ClientService {
    Client create(ClientRequest clientRequest);
    ClientRequest findById(Long id);
    List<Client> findAll();
    void delete(Long id);
    ClientRequest update(Client client);
    Client findClient(Long id);
}
