package com.javainterns.bookingroom.service;

import java.util.List;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;

public interface RoomService {

    RoomRequest create(RoomRequest roomRequest);
    RoomRequest findById(Long id);
    List<RoomRequest> findAll();
    RoomRequest update(Room room);
    void delete(Long id);
    Room findRoom(Long id);
}
