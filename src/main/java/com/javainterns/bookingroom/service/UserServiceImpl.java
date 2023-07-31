package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Client create(Client CLient) {
        userRepository.save(CLient);
        return CLient;
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> findUserOptional = userRepository.findById(id);
        Client findClient = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(findClient);

    }

    @Override
    public List<Client> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Client update(Client CLient) {
        return userRepository.save(CLient);
    }
}
