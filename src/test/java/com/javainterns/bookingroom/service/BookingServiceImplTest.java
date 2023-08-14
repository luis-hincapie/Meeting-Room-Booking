package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.javainterns.bookingroom.model.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@EnableWebSecurity
public class BookingServiceImplTest {

    @Autowired
    BookingService bookingService;

    @DisplayName("Create a Booking")
    @Test
    @WithMockUser(username = "David", roles = "USER")
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
    @WithMockUser(username = "David", roles = "USER")
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
