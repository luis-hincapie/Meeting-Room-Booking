package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.ClientRequest;
import com.javainterns.bookingroom.model.mapper.ClientRequestMapper;
import com.javainterns.bookingroom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientRequestMapper clientRequestMapper;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientRequestMapper clientRequestMapper){
        this.clientRepository = clientRepository;
        this.clientRequestMapper = clientRequestMapper;
    }

    @Override
    public Client create(ClientRequest clientRequest) {
        Client client = clientRequestMapper.toClient(clientRequest);
        return clientRepository.save(client);
    }

    @Override
    public ClientRequest findById(Long id) {
        return clientRequestMapper.toClientRequest(
                clientRepository.findById(id).orElseThrow(
                        ()->new NoRecordFoundException("Client Record Not Found")));

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) throw new NoRecordFoundException("Client Record Not Found");
        clientRepository.deleteById(id);
    }

    @Override
    public ClientRequest update(Client CLient) {
        return clientRequestMapper.toClientRequest(clientRepository.save(CLient));
    }

    @Override
    public Client findClient(Long id) {
        return clientRepository.findById(id).orElseThrow(()-> new NoRecordFoundException("Client Record Not Found"));
    }
}
