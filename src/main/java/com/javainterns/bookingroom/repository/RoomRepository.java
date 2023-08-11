package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByIsActiveTrue();

}
