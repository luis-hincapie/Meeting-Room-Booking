package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomRequestMapper roomRequestMapper;

    @Override
    public Room create(RoomRequest roomRequest) {
        Room room = roomRequestMapper.toRoom(roomRequest);
        return roomRepository.save(room);
    }

    @Override
    public Room findById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Room Record Not Found"));
        if(!room.isActive()) throw  new NoRecordFoundException("Room Record Not Found");
        return room;

    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll().stream().filter(x ->x.isActive()).collect(Collectors.toList());
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        if (!roomRepository.existsById(id)) throw new NoRecordFoundException("Room Record Not Found");
        roomRepository.deleteById(id);
    }

    @Override
    public Room findRoom(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Room Record Not Found"));

    }

}
