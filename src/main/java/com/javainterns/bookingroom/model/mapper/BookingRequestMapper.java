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
    /*booking.setDate(bookingRequest.getDate());
    booking.setStartTime(bookingRequest.getStartTime());
    booking.setEndTime(bookingRequest.getEndTime());*/
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
    /*bookingRequest.setId(booking.getId());
    bookingRequest.setDate(booking.getDate());
    bookingRequest.setEndTime(booking.getEndTime());
    bookingRequest.setStartTime(booking.getStartTime());
    bookingRequest.setRoomId(booking.getRoom().getId());*/
    return bookingRequest;
  }
}
