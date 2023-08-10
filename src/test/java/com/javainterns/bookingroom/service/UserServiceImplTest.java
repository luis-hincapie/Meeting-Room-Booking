package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.BookingroomApplication;
import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        User user = new User(null, "dgomeee@gmail.com", "davito", "1234", null, null);
        assertEquals(user, userService.create(user));
    }

    @Test
    void findById() {
        User user = userService.findById(10L);
//        assertNotNull(user);
    }

    @Test
    void delete() {
        User user = userService.findById(1L);
        userService.delete(1L);
        assertNotEquals(user, userService.findById(1L));
    }

    @Test
    void findAll() {
    }

    @Test
    void findByUsername() {
    }
}