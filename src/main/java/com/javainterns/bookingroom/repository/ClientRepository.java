package com.javainterns.bookingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainterns.bookingroom.model.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
}
