package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    public void createRoom(Room room);
    public Optional<Room> getByIdRoom(long id);
    public List<Room> getRooms();
    public Room updateRoom(Room room);
    public void deleteRoom(Long id);
}
