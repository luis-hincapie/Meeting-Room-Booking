package com.javainterns.bookingroom.service;
import java.util.List;
import com.javainterns.bookingroom.model.Booking;
import org.springframework.stereotype.Service;

public interface BookingService {
    Boolean create(Booking booking);
    Boolean update(Booking booking);
    Booking findById(Long id);
    Boolean delete(Booking booking);
    List<Booking> findAll();
}
