package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.javainterns.bookingroom.model.User;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.web.servlet.function.ServerResponse.status;


@SpringBootTest
@EnableWebSecurity
@AutoConfigureMockMvc
public class BookingServiceImplTest {

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

    //Integration Test

    @Test
    @WithMockUser(username = "David", roles = "ADMIN")
    public void getBookingsAdminIsStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookingsAdminIsStatus401Unauthorized() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }
    @Test
    @WithMockUser(username = "David", roles = "USER")
    public void getBookingsAdminIsStatus403isForbidden() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "David", roles = "USER")
    public void getBookingsUserIsStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "David", roles = "USER")
    public void postBookingIsStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                        .content("{\"roomId\" : \"1\",\"date\" : \"2023-09-28\", \"startTime\" : \"09:00\",\"endTime\" : \"16:00\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void getBookingsUserIsStatus401Unauthorized() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }


























}
