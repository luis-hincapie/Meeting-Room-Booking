package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import java.util.List;

public interface RoomService {
  RoomRequest create(RoomRequest roomRequest);
  RoomRequest findById(Long id);
  List<RoomRequest> findAll();
  RoomRequest update(RoomRequest roomRequest);
  void delete(Long id);
  Room findRoom(Long id);
}
