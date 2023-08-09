package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Booking;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
  @Query("SELECT b FROM Booking b WHERE b.date = ?1 AND b.room.id = ?2")
  List<Booking> findBookingsByRoomIdAndDate(LocalDate date, Long id);

  Optional<Booking> findByIdAndUser_Username(Long id, String username);

  List<Booking> findByUser_Username(String username);

}
