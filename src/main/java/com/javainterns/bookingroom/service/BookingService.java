package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.dto.BookingRequest;

import java.util.List;

public interface BookingService {
    BookingRequest create(BookingRequest bookingRequest);
    BookingRequest findById(Long id);
    Boolean delete(Long id);
    List<BookingRequest> findAll();
}
