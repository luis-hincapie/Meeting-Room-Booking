package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Room;

public interface RoomService {

    public void create(Room room);
    public Optional<Room> findById(long id);
    public List<Room> findAll();
    public Room update(Room room);
    public void delete(Long id);
}
