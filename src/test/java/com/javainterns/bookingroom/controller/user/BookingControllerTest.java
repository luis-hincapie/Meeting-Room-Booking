package com.javainterns.bookingroom.controller.user;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableWebSecurity
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithMockUser(username = "David", roles = "USER")
    public void getBookingsUserIsStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "David", roles = "USER")
    public void postBookingUserIsStatus200() throws Exception {

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