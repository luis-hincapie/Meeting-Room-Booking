package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.javainterns.bookingroom.model.dto.ClientRequest;
import com.javainterns.bookingroom.model.mapper.ClientRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientRequestMapper clientRequestMapper;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientRequest create(ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        return clientRequestMapper.toClientRequest(clientRepository.save(client));
    }

    @Override
    public ClientRequest findById(Long id) {
        Optional<Client> findUserOptional = clientRepository.findById(id);
        Client findClient = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return clientRequestMapper.toClientRequest(findClient);

    }

    @Override
    public List<ClientRequest> findAll() {
        return clientRepository.findAll().stream().map(x -> clientRequestMapper
                .toClientRequest(x)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientRequest update(Client CLient) {
        return clientRequestMapper.toClientRequest(clientRepository.save(CLient));
    }

    @Override
    public Client findClient(Long id) {
        Optional<Client> findUserOptional = clientRepository.findById(id);
        return findUserOptional.orElseThrow(EntityNotFoundException::new);
    }
}
