package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.UserDto;
import com.javainterns.bookingroom.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    private static List<UserDto> userDtoList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        userDtoList.add(
                UserDto.builder().id(null).email("j@gmail.com").username("juan").password("1234").roles(null).build());
        userDtoList.add(
                UserDto.builder().id(null).email("a@gmail.com").username("aleja").password("1234").roles(null).build());
        userDtoList.add(
                UserDto.builder().id(null).email("m@gmail.com").username("maria").password("1234").roles(null).build());
    }

    @Test
    @Order(1)
    void createNewUser() {
        userDtoList.stream().forEach(userDto -> {
            //When
            UserDto userDtoCreated = userService.create(userDto);
            //Then
            assertNotNull(userDtoCreated);
            assertEquals(userService.findById(userDtoCreated.getId()).getEmail(), userDto.getEmail());
            userDto.setId(userDtoCreated.getId());
        });
    }

    @Test
    @Order(2)
    void findByIdUserAlreadyStored() {
        userDtoList.stream().forEach(userDto -> {
            //When
            User userFound = userService.findById(userDto.getId());
            //Then
            assertNotNull(userFound);
            assertEquals(userFound.getId(),userDto.getId());
            assertEquals(userFound.getUsername(), userDto.getUsername());
        });
    }

    @ParameterizedTest
    @CsvSource({"10", "20", "30", "40", "50"})
    void findByIdUserThatDoesntExist(long id) {
        if (!userRepository.existsById(id)) assertThrows(NoRecordFoundException.class,()->userService.findById(id));
    }

    @Test
    @Order(4)
    void deleteAnExistUser() {
        userDtoList.stream().forEach(userDto -> {
            //When
            assertDoesNotThrow(()->userService.findById(userDto.getId()));
            //Then
            assertTrue(userService.delete(userDto.getId()));
            assertThrows(NoRecordFoundException.class, ()->userService.delete(userDto.getId()));
        });
    }

    @ParameterizedTest
    @CsvSource({"5", "8", "100", "50"})
    void deleteNonexistentUser(long id){
        if (!userRepository.existsById(id)) assertThrows(NoRecordFoundException.class, ()->userService.delete(id));
    }

    @Test
    void findAll() {
    }

    @Order(3)
    @Test
    void findUserByUsername() {
        userDtoList.stream().forEach(userDto -> {
            User user = userService.findByUsername(userDto.getUsername());
            assertNotNull(user);
            assertEquals(user.getUsername(), userDto.getUsername());
            assertEquals(user.getEmail(), userDto.getEmail());
        });
    }

    @Test
    @Order(5)
    void findUserNonexistentByUsername() {
        userDtoList.stream().forEach(userDto -> {
            assertThrows(NoRecordFoundException.class, ()->userService.findByUsername(userDto.getUsername()));
        });
    }
}