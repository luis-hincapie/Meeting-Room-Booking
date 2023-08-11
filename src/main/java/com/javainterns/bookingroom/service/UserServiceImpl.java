package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.UserDto;
import com.javainterns.bookingroom.model.mapper.UserMapper;
import com.javainterns.bookingroom.repository.RoleRepository;
import com.javainterns.bookingroom.repository.UserRepository;
import com.javainterns.bookingroom.utils.Messages;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Messages messages;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, Messages messages, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.messages = messages;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()))
            throw new NoRecordFoundException(messages.get("user.email.exists"));
        if (userRepository.existsByUsername(userDto.getUsername()))
            throw new NoRecordFoundException(messages.get("user.username.exists"));

        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findById(2)));
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("User not found with id: " + id));
    }

    @Override
    public Boolean delete(Long id) {
        if (!userRepository.existsById(id))
            throw new NoRecordFoundException(messages.get("user.record.not.found"));
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoRecordFoundException("User not found with username: " + username));
    }


}
