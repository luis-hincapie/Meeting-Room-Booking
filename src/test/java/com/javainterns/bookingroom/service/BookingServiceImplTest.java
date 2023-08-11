package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.repository.BookingRepository;
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


@SpringBootTest
@EnableWebSecurity
public class BookingServiceImplTest {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @Test
    @WithMockUser(username = "David", roles = "USER")
    void testSaveBoking(){

        BookingRequest booking1 = BookingRequest.builder()
                .roomId(1L)
                .date(LocalDate.parse("2023-11-20"))
                .startTime(LocalTime.parse("12:00"))
                .endTime(LocalTime.parse("13:00"))
                .build();

        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //BookingRequest bookingSave = bookingService.create(booking1, userDetails);
        //User user = new User(null, "dgomeee@gmail.com", "davito", "1234", null, null);

        Principal principal = () -> "David"; // Replace with actual username

        BookingRequest bookingSave = bookingService.create(booking1, principal);

        assertThat(bookingSave).isNotNull();


    }








}
