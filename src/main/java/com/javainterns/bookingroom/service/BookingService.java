package com.javainterns.bookingroom.service;
import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;

public interface BookingService {
    BookingRequest create(BookingRequest bookingRequest);
    BookingRequest update(Booking booking);
    BookingRequest findById(Long id);
    Boolean delete(Long id);
    List<BookingRequest> findAll();
}
