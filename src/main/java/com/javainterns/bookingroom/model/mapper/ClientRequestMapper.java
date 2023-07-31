package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper {

    public Client toClient(ClientRequest clientRequest) {
        return new Client(clientRequest.getName(), clientRequest.getEmail());
    }

    public ClientRequest toClientRequest(Client client) {
        return new ClientRequest(client.getName(), client.getEmail());
    }
}
