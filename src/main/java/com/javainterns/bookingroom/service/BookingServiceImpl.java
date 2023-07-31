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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingRequestMapper bookingRequestMapper;
    @Autowired
    ClientService clientService;
    @Autowired
    RoomService roomService;

    @Override
    public BookingRequest create(BookingRequest bookingRequest) {
        Booking booking = bookingRequestMapper.toBooking(bookingRequest);
        Client client = clientService.findById(bookingRequest.getUserId()).get();
        Room room = roomService.findById(bookingRequest.getRoomId()).get();
        booking.setUser(client);
        booking.setRoom(room);
        return bookingRequestMapper.toBookingRequest(bookingRepository.save(booking));
    }

    @Override
    public BookingRequest update(Booking booking) {
        return bookingRequestMapper.toBookingRequest(bookingRepository.save(booking));
    }

    @Override
    public Boolean delete(Long id) {
        if (bookingRepository.existsById(id)) {
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
    public List<BookingRequest> findAll() {
        return bookingRepository.findAll().stream().map(x -> bookingRequestMapper
                .toBookingRequest(x)).collect(Collectors.toList());
    }
}
