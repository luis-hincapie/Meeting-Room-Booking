package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.repository.RoomRepository;
import org.springframework.validation.annotation.Validated;

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
        return roomRepository.findAll();
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
