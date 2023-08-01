package com.javainterns.bookingroom.repository;

import com.javainterns.bookingroom.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where b.room.id = ?1 and b.date = ?2")
    List<Booking> findByRoom_IdAndDate(Long id, LocalDate date);


    // List<Booking> findByDateAndRoom(Date date, Long id);
    // @Query("SELECT END_TIME, START_TIME FROM BOOKING WHERE DATE = ?1 AND ROOM_ID
    // = ?2")
    // Optional<List<Booking>> findBookingsByRoomIdAndDate(Date date, Long id);
}
