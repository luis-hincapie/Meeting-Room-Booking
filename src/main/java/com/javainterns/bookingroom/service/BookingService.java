package com.javainterns.bookingroom.service;
import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;

public interface BookingService {
    Booking create(BookingRequest bookingRequest);
    Booking update(Booking booking);
    Optional<Booking> findById(Long id);
    Boolean delete(Long id);
    List<Booking> findAll();
}
