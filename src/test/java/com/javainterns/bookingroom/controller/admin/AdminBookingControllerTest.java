package com.javainterns.bookingroom.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableWebSecurity
@AutoConfigureMockMvc
class AdminBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

}