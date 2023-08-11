package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import org.springframework.stereotype.Component;

@Component
public class RoomRequestMapper {
    public Room toRoom(RoomRequest roomRequest) {
        return new Room(
                roomRequest.getId(),
                roomRequest.getName(),
                roomRequest.getLocation(),
                roomRequest.getCapacity(),
                roomRequest.getStartTime(),
                roomRequest.getFinishTime(),
                roomRequest.getActive()
        );
    }

    public RoomRequest toRoomRequest(Room room) {
        return new RoomRequest(
                room.getId(),
                room.getName(),
                room.getLocation(),
                room.getCapacity(),
                room.getStartTime(),
                room.getFinishTime(),
                room.isActive()
        );
    }
}
