package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
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
        Client client = clientService.findClient(bookingRequest.getUserId());
        Room room = roomService.findRoom(bookingRequest.getRoomId());
        booking.setUser(client);
        booking.setRoom(room);
        return bookingRequestMapper.toBookingRequest(bookingRepository.save(booking));
    }

    @Override
    public Boolean delete(Long id) {
        if (!bookingRepository.existsById(id)) throw new NoRecordFoundException("Booking Record Not Found");
        bookingRepository.deleteById(id);
        return true;
    }

    @Override
    public BookingRequest findById(Long id) {
        return bookingRequestMapper.toBookingRequest(
                bookingRepository.findById(id).orElseThrow(
                        ()-> new NoRecordFoundException("Booking Record not found")));
    }

    @Override
    public List<BookingRequest> findAll() {
        return bookingRepository.findAll().stream().map(
                x -> bookingRequestMapper.toBookingRequest(x)).collect(Collectors.toList());
    }
}
