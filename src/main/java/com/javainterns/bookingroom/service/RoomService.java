package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;

import java.util.List;

public interface RoomService {

    Room create(RoomRequest roomRequest);
    Room findById(Long id);
    List<Room> findAll();
    Room update(Room room);
    void delete(Long id);
    Room findRoom(Long id);
}
