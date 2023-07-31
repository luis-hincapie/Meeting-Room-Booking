package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client CLient) {
        clientRepository.save(CLient);
        return CLient;
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> findUserOptional = clientRepository.findById(id);
        Client findClient = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(findClient);

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(Client CLient) {
        return clientRepository.save(CLient);
    }
}
