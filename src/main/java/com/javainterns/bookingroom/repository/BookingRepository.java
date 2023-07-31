package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long ID);
    List<Booking> findByDateAndRoom(Date date, Long id);

//    @Query("SELECT END_TIME, START_TIME FROM BOOKING WHERE DATE  = ?1 AND ROOM_ID = ?2")
//    Optional<List<Booking>> findBookingsByRoomIdAndDate(Date date, Long id);
}
