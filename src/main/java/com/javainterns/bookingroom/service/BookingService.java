package com.javainterns.bookingroom.service;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import org.springframework.stereotype.Service;

public interface BookingService {
    BookingRequest create(BookingRequest bookingRequest);
    BookingRequest update(Booking booking);
    Optional<Booking> findById(Long id);
    Boolean delete(Long id);
    List<BookingRequest> findAll();
}
