package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.RoomRepository;
import com.javainterns.bookingroom.utils.TimeValidator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoomServiceTests {

    @Mock
    private TimeValidator timeValidator;

    @Mock
    private RoomRequestMapper roomRequestMapper;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private StartTimeIsGreaterThanEndTime startTimeIsGreaterThanEndTime;

    @InjectMocks
    private RoomServiceImpl roomService;

    private RoomRequest roomRequest;
    @BeforeEach
    void setUp() {


    }

    @DisplayName("Given RoomRequest when createRoom then return RoomRequest")
    @ParameterizedTest
    @CsvSource({"00:00,01:00", "01:28,02:30", "23:00,23:59", "00:00,23:59", "11:00,23:00"})
    void givenRoomRequest_whenCreateRoom_theReturnRoomRequest(LocalTime startTime, LocalTime finishTime) {
        roomRequest = RoomRequest.builder()
                .id(null)
                .name("Room 1")
                .startTime(startTime)
                .finishTime(finishTime)
                .isActive(true)
                .build();

        // Given roomRequest
        given(roomService.create(roomRequest)).willReturn(roomRequest);

        // When
        RoomRequest roomRequestCreated = roomService.create(roomRequest);

        // Then
        assertNotNull(roomRequestCreated);
        assertInstanceOf(Long.class, roomRequestCreated.getId());
        assertEquals(roomRequest.getName(), roomRequestCreated.getName());
        assertEquals(roomRequest.getStartTime(), roomRequestCreated.getStartTime());
        assertEquals(roomRequest.getFinishTime(), roomRequestCreated.getFinishTime());
        assertEquals(roomRequest.getIsActive(), roomRequestCreated.getIsActive());

    }


    @DisplayName("Given a Bad RoomRequest when createRoom then return RoomRequest")
    @ParameterizedTest
    @CsvSource({"01:00,00:00", "03:28,02:30", "23:59,23:00", "23:59,00:00", "23:00,11:00"})
    void givenBadRoomRequest_whenCreateRoom_thenThrowsException(LocalTime startTime, LocalTime finishTime) {
        roomRequest = RoomRequest.builder()
                .id(null)
                .name("Room 1")
                .startTime(startTime)
                .finishTime(finishTime)
                .isActive(true)
                .build();
        // Given roomRequest
        given(roomService.create(roomRequest)).willReturn(roomRequest);

        // When
        assertThrows(StartTimeIsGreaterThanEndTime.class, () -> roomService.create(roomRequest));

        // Then
        verify(roomRepository,never()).save(any(Room.class));

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findRoom() {
    }
}