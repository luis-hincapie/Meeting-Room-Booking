package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientRequest create(ClientRequest clientRequest);
    ClientRequest findById(Long id);
    List<ClientRequest> findAll();
    void delete(Long id);
    ClientRequest update(ClientRequest clientRequest);
    Client finClient(Long id);
    JwtResponse authenticate(LoginRequest loginRequest);

    Client registerUser(SignupRequest signUpRequest);
    Boolean changePassword(ChangePasswordRequest changePasswordRequest);
}
