package com.javainterns.bookingroom.service;
import java.util.List;
import com.javainterns.bookingroom.model.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    Booking create(Booking booking);
    Booking update(Booking booking);
    Booking findById(Long id);
    boolean delete(Booking booking);
    List<Booking> findAll();
}
