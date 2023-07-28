package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> findUserOptional = userRepository.findById(id);
        User findUser = findUserOptional.orElseThrow(EntityNotFoundException::new);
        return Optional.ofNullable(findUser);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
