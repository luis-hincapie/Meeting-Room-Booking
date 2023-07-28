package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User resgistration(User user) {
        userRepository.save(user);
        return user;
    }
}
