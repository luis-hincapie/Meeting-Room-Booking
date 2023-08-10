package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;

public interface RoleService {
    
    Role findByName(ERole name);

}
