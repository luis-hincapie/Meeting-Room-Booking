package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.ERole;
import com.javainterns.bookingroom.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
