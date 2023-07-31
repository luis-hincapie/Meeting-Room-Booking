package com.javainterns.bookingroom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomRequestMapper roomRequestMapper;
    @Override
    public RoomRequest create(RoomRequest roomRequest) {
        Room room = roomRequestMapper.toRoom(roomRequest);
        return  roomRequestMapper.toRoomRequest(roomRepository.save(room));
    }

    @Override
    public RoomRequest findById(Long id) {
        Optional<Room> findUserOptional = roomRepository.findById(id);
        Room findUser = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return roomRequestMapper.toRoomRequest(findUser);

    }

    @Override
    public List<RoomRequest> findAll() {
        return roomRepository.findAll().stream().map(x -> roomRequestMapper
                        .toRoomRequest(x)).collect(Collectors.toList());
    }

    @Override
    public RoomRequest update(Room room) {
        return roomRequestMapper.toRoomRequest( roomRepository.save(room));
    }
    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room findRoom(Long id) {
        Optional<Room> findUserOptional = roomRepository.findById(id);
        return findUserOptional.orElseThrow(EntityNotFoundException::new);

    }

}
