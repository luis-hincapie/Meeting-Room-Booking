package com.javainterns.bookingroom.model.mapper;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class BookingRequestMapper {
    public Booking toBooking(BookingRequest bookingRequest){
        Booking booking = new Booking();
        booking.setDate(bookingRequest.getDate());
        booking.setStartTime(bookingRequest.getStartTime());
        booking.setEndTime(bookingRequest.getEndTime());
        return booking;
    }
}
