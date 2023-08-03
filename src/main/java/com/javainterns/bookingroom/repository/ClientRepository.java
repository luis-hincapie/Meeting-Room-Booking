package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {

    Optional<Client> findByUsername(String username);

    Optional<Client> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
