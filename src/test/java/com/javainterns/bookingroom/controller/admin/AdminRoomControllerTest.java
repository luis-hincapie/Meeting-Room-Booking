package com.javainterns.bookingroom.controller.admin;

import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.RoomRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(AdminRoomController.class)
class AdminRoomControllerTest {
    @MockBean
    private AdminRoomController adminRoomController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

/*    @ParameterizedTest
    @CsvSource({"00:00,01:00", "01:28,02:30", "23:00,23:59", "00:00,23:59", "11:00,23:00"})
    void return201WhenCreateRoom(LocalTime startTime, LocalTime finishTime) throws Exception{
        RoomRequest roomRequest = RoomRequest.builder()
                .name("Room 1")
                .startTime(startTime)
                .finishTime(finishTime)
                .build();
        when(adminRoomController.create(roomRequest)).thenReturn(new ResponseEntity<>(roomRequest, HttpStatus.CREATED));

        mockMvc.perform(post("/admin/room")
                .contentType(MediaType.APPLICATION_JSON)


    }*/

}