package com.javainterns.bookingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainterns.bookingroom.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
