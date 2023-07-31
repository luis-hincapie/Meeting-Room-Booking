package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class RoomServiceImpl implements RoomService{
    @Autowired
    RoomRepository roomRepository;
    @Override
    public void create(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Optional<Room> findById(long id) {
        Optional<Room> findUserOptional = roomRepository.findById(id);
        Room findUser = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(findUser);

    }

    @Override
    public List<Room> findAll() {

        List<Room> room = new ArrayList();
        room = roomRepository.findAll();
        return room;
    }

    @Override
    public Room update(Room room) {
        roomRepository.save(room);
        return room;
    }
    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

}
