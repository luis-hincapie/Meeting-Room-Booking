package com.javainterns.bookingroom.service;

import java.util.List;

import com.javainterns.bookingroom.model.User;

public interface UserService {
  User create(User user);
  User findById(Long id);
  Boolean delete(Long id);
  List<User> findAll();
  User findByUsername(String username);
}
