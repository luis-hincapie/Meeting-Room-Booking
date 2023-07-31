package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingRequestMapper bookingRequestMapper;
    @Autowired
    ClientService clientService;
    @Autowired
    RoomService roomService;
    @Override
    public Booking create(BookingRequest bookingRequest) {
        Booking booking = bookingRequestMapper.toBooking(bookingRequest);
        Client client = clientService.findById(bookingRequest.getUserId()).get();
        Room room = roomService.findById(bookingRequest.getRoomId()).get();
        booking.setUser(client);
        booking.setRoom(room);
        return bookingRepository.save(booking);
    }
    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Boolean delete(Long id) {
        if(bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
