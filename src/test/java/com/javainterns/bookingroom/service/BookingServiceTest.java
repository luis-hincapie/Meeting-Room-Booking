package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.nio.file.Paths.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.web.servlet.function.ServerResponse.status;


@SpringBootTest
@EnableWebSecurity
@AutoConfigureMockMvc
public class BookingServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    BookingService bookingService;

    //Unit Test

    @DisplayName("Create a Booking")
    @Test
    void testCreateBoking(){
        //Arrange
        BookingRequest booking = BookingRequest.builder()
                .roomId(1L)
                .date(LocalDate.parse("2023-11-20"))
                .startTime(LocalTime.parse("12:00"))
                .endTime(LocalTime.parse("13:00"))
                .build();

        Principal principal = () -> "David";

        //Act
        BookingRequest bookingSave = bookingService.create(booking, principal);

        //Assert
        assertThat(bookingSave).isNotNull();
        assertThat(bookingSave.getId()).isGreaterThan(0);


    }

    @DisplayName("StartTime Is Greater Than EndTime In Booking Exception")
    @Test
    void StartTimeIsGreaterThanEndTimeInBookingThrowsException(){
        //Arrange
        BookingRequest booking = BookingRequest.builder()
                .roomId(1l)
                .date(LocalDate.parse("2023-11-20"))
                .startTime(LocalTime.parse("13:00"))
                .endTime(LocalTime.parse("12:00"))
                .build();

        Principal principal = () -> "David";

        //Assert
        assertThrows(StartTimeIsGreaterThanEndTime.class,()-> bookingService.create(booking, principal));

    }


}
