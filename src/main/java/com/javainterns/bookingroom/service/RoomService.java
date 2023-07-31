package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    public void create(Room room);
    public Optional<Room> findById(long id);
    public List<Room> findAll();
    public Room update(Room room);
    public void delete(Long id);
}
