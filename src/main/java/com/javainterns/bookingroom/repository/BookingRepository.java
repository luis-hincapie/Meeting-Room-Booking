package com.javainterns.bookingroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainterns.bookingroom.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // List<Booking> findByDateAndRoom(Date date, Long id);

    // @Query("SELECT END_TIME, START_TIME FROM BOOKING WHERE DATE = ?1 AND ROOM_ID
    // = ?2")
    // Optional<List<Booking>> findBookingsByRoomIdAndDate(Date date, Long id);
}
