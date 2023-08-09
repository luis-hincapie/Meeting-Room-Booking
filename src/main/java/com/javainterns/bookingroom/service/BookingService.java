package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;

import java.security.Principal;
import java.util.List;

public interface BookingService {
  BookingRequest create(BookingRequest bookingRequest, Principal principal);
  BookingRequest findById(Long id, Principal principal);
  Boolean delete(Long id);
  List<BookingRequest> findAll();
  List<Booking> findBookingsByUsername(String username);

  Boolean deleteByUser(Long id, Principal principal);
}
