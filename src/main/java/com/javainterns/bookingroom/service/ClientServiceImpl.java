package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;
import com.javainterns.bookingroom.model.mapper.ClientRequestMapper;
import com.javainterns.bookingroom.repository.ClientRepository;
import com.javainterns.bookingroom.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENT_NOT_FOUND = "client.record.not.found";
    private final ClientRepository clientRepository;
    private final ClientRequestMapper clientRequestMapper;
    private final Messages messages;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientRequestMapper clientRequestMapper, Messages messages) {
        this.clientRepository = clientRepository;
        this.clientRequestMapper = clientRequestMapper;
        this.messages = messages;
    }

    @Override
    public ClientRequest create(ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        return clientRequestMapper.toClientRequest(clientRepository.save(client));
    }

    @Override
    public ClientRequest findById(Long id) {
        return clientRequestMapper.toClientRequest(clientRepository.findById(id).orElseThrow(() -> new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND))));

    }

    @Override
    public List<ClientRequest> findAll() {
        return clientRepository.findAll().stream().map(clientRequestMapper::toClientRequest).toList();
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) throw new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND+id));
        clientRepository.deleteById(id);
    }

    @Override
    public ClientRequest update(ClientRequest clientRequest) {
        Optional<Client> client = clientRepository.findById(clientRequest.getId());
        if (client.isEmpty()) throw new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND+clientRequest.getId()));
        Client savedClient = clientRepository.save(clientRequestMapper.toClient(clientRequest));
        return clientRequestMapper.toClientRequest(savedClient);
    }

    @Override
    public Client finClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND)));

    }
}
