package com.javainterns.bookingroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.RoomRepository;
import com.javainterns.bookingroom.utils.Messages;
import com.javainterns.bookingroom.utils.TimeValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

  private static final String ROOM_NOT_FOUND = "room.not.found";
  private static final String RANGE_TIME_ERROR =
    "start-time.could.not.be.greater.than.finish-time";

  private final RoomRepository roomRepository;
  private final RoomRequestMapper roomRequestMapper;
  private final TimeValidation timevalidation;
  private final Messages messages;

  @Override
  public RoomRequest create(RoomRequest roomRequest) {
    if (
      Boolean.FALSE.equals((
        timevalidation.isValidTimeRange(
          roomRequest.getFinishTime(),
          roomRequest.getStartTime()
        )
      ))
    ) throw new StartTimeIsGreaterThanEndTime(messages.get(RANGE_TIME_ERROR));
    Room room = roomRequestMapper.toRoom(roomRequest);
    return roomRequestMapper.toRoomRequest(roomRepository.save(room));
  }

  @Override
  public RoomRequest findById(Long id) {
    Room room = roomRepository
      .findById(id)
      .orElseThrow(() ->
        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
      );
    if (Boolean.FALSE.equals(room.getIsActive())) throw new NoRecordFoundException(
      messages.get(ROOM_NOT_FOUND)
    );
    return roomRequestMapper.toRoomRequest(room);
  }

  @Override
  public List<RoomRequest> findAll() {
    return roomRepository
      .findAll()
      .stream()
      .filter(Room::getIsActive)
      .map(roomRequestMapper::toRoomRequest)
      .toList();
  }

  @Override
  public RoomRequest update(RoomRequest roomRequest) {
    if (
      Boolean.FALSE.equals((
        timevalidation.isValidTimeRange(
          roomRequest.getFinishTime(),
          roomRequest.getStartTime()
        )
      ))
    ) throw new StartTimeIsGreaterThanEndTime(messages.get(RANGE_TIME_ERROR));
    roomRepository
      .findById(roomRequest.getId())
      .orElseThrow(() ->
        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
      );
    Room room = roomRequestMapper.toRoom(roomRequest);
    return roomRequestMapper.toRoomRequest(roomRepository.save(room));
  }

  @Override
  public void delete(Long id) {
    if (!roomRepository.existsById(id)) throw new NoRecordFoundException(
      messages.get(ROOM_NOT_FOUND)
    );
    roomRepository.deleteById(id);
  }

  @Override
  public Room findRoom(Long id) {
    Room room = roomRepository
      .findById(id)
      .orElseThrow(() ->
        new NoRecordFoundException(messages.get(ROOM_NOT_FOUND))
      );
    if (Boolean.FALSE.equals(room.getIsActive())) throw new NoRecordFoundException(
      messages.get(ROOM_NOT_FOUND)
    );
    return room;
  }
}
