package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.RoomRepository;
import com.javainterns.bookingroom.utils.TimeValidation;
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
    @Autowired
    TimeValidation timevalidation;

    @Override
    public RoomRequest create(RoomRequest roomRequest) {
        if(!timevalidation.isValidTimeRange(roomRequest.getFinishTime(),roomRequest.getStartTime()))
            throw new StartTimeIsGreaterThanEndTime("startTime could not be greater than finishTime");
        Room room = roomRequestMapper.toRoom(roomRequest);
        return roomRequestMapper.toRoomRequest(roomRepository.save(room));
    }

    @Override
    public RoomRequest findById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Room Record Not Found"));
        if(!room.isActive()) throw  new NoRecordFoundException("Room Record Not Found");
        return roomRequestMapper.toRoomRequest(room);
    }

    @Override
    public List<RoomRequest> findAll() {
        return roomRepository.findAll().stream().filter(x ->x.isActive()).map(x -> roomRequestMapper.toRoomRequest(x)).collect(Collectors.toList());
    }

    @Override
    public RoomRequest update(RoomRequest roomRequest) {
        if(!timevalidation.isValidTimeRange(roomRequest.getFinishTime(),roomRequest.getStartTime()))
            throw new StartTimeIsGreaterThanEndTime("startTime could not be greater than finishTime");
        Room room = roomRequestMapper.toRoom(findById(roomRequest.getId()));
        return roomRequestMapper.toRoomRequest(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        if (!roomRepository.existsById(id)) throw new NoRecordFoundException("Room Record Not Found");
        roomRepository.deleteById(id);
    }


}
