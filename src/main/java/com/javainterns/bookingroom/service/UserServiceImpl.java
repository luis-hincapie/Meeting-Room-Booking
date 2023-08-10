package com.javainterns.bookingroom.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;
import com.javainterns.bookingroom.utils.Messages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;
  private final Messages messages;
  private final PasswordEncoder passwordEncoder;

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

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new NoRecordFoundException("User not found with username: " + username));
  }


}
