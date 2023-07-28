package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    Optional<User> findById(Long id);

    List<User> findAll();

    void delete(Long id);

    User update(User user);
}
