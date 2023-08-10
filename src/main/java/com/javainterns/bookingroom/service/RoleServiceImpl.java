package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import com.javainterns.bookingroom.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository roleRepository;

  @Override
  public Role findByName(ERole name) {
    return roleRepository
      .findByName(name)
      .orElseThrow(() -> new NoRecordFoundException("Role is not found."));
  }
}
