package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.repository.UserRepository;
import com.javainterns.bookingroom.utils.Messages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  Messages messages;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public User create(User user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return userRepository.save(user);
  }

  @Override
  public User findById(Long id) {
    return userRepository
      .findById(id)
      .orElseThrow(() ->
        new NoRecordFoundException("User not found with id: " + id)
      );
  }

  @Override
  public Boolean delete(Long id) {
    if (!userRepository.existsById(id)) throw new NoRecordFoundException(
      messages.get("booking.record.not.found")
    );
    userRepository.deleteById(id);
    return true;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }


}
