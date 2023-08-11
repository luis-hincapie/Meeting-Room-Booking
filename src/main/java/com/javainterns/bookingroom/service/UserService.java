package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto create(UserDto user);
  User findById(Long id);
  Boolean delete(Long id);
  List<UserDto> findAll();
  User findByUsername(String username);
}
