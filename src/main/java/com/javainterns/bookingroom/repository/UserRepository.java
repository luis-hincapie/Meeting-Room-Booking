package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <Client, Long> {
}
