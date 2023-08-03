package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import com.javainterns.bookingroom.model.dto.*;
import com.javainterns.bookingroom.model.mapper.ClientRequestMapper;
import com.javainterns.bookingroom.repository.ClientRepository;
import com.javainterns.bookingroom.repository.RoleRepository;
import com.javainterns.bookingroom.security.jwt.JwtUtils;
import com.javainterns.bookingroom.security.service.UserDetailsImpl;
import com.javainterns.bookingroom.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENT_NOT_FOUND = "client.record.not.found";
    private final ClientRepository clientRepository;
    private final ClientRequestMapper clientRequestMapper;
    private final Messages messages;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClientRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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
        if (!clientRepository.existsById(id)) throw new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND + id));
        clientRepository.deleteById(id);
    }

    @Override
    public ClientRequest update(ClientRequest clientRequest) {
        Optional<Client> client = clientRepository.findById(clientRequest.getId());
        if (client.isEmpty()) throw new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND + clientRequest.getId()));
        Client savedClient = clientRepository.save(clientRequestMapper.toClient(clientRequest));
        return clientRequestMapper.toClientRequest(savedClient);
    }

    @Override
    public Client finClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NoRecordFoundException(messages.get(CLIENT_NOT_FOUND)));

    }

    public JwtResponse authenticate(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public Client registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("existsByUsername");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("existsByEmail");
        }

        // Create new user's account
        Client user = new Client(
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) {

        boolean isWithinDb = false;

        Optional<Client> user = Optional.empty();

        //Check if it is a username or email and try to find if it exists in the database
        if (changePasswordRequest.getUsername() != null) {
            isWithinDb = userRepository.existsByUsername(changePasswordRequest.getUsername());

            user = userRepository.findByUsername(changePasswordRequest.getUsername());

        } else if (!isWithinDb && changePasswordRequest.getEmail() != null) {
            isWithinDb = userRepository.existsByEmail(changePasswordRequest.getEmail());

            user = userRepository.findByEmail(changePasswordRequest.getEmail());

        }

        if (isWithinDb) {
            //The user exists in db
            //Check if the password and verifyPassword are the same
            changeUserPassword(user.get(), changePasswordRequest.getPassword());

            return true;

        } else {

            return true;

        }


    }

    public void changeUserPassword(Client user, String password) {
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

}
