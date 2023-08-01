package com.javainterns.bookingroom.repository;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javainterns.bookingroom.model.Booking;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
     @Query("SELECT b FROM Booking b WHERE b.date = ?1 AND b.room.id = ?2")
     List<Booking> findBookingsByRoomIdAndDate(LocalDate date, Long id);
}
