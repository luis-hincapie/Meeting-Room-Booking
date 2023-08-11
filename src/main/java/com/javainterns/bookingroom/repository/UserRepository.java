package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  @Query("select u from User u where u.username = ?1")
  Optional<User> getName(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
