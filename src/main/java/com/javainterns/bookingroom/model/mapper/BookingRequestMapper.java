package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestMapper {

  public Booking toBooking(BookingRequest bookingRequest) {
    Booking booking = new Booking(
            null,
            null,
            null,
            bookingRequest.getStartTime(),
            bookingRequest.getEndTime(),
            bookingRequest.getDate()
    );
    return booking;
  }

  public BookingRequest toBookingRequest(Booking booking) {
    BookingRequest bookingRequest = new BookingRequest(
            booking.getId(),
            booking.getRoom().getId(),
            booking.getDate(),
            booking.getStartTime(),
            booking.getEndTime()
    );
    return bookingRequest;
  }
}
