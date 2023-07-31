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
        Client client = clientService.findById(bookingRequest.getUserId()).get();
        Room room = roomService.findById(bookingRequest.getRoomId()).get();
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
        Optional<Booking> booking =  bookingRepository.findById(id);
        if(!booking.isPresent())throw new NoRecordFoundException("Booking Record Not Found");
        return bookingRequestMapper.toBookingRequest(booking.get());
    }

    @Override
    public List<BookingRequest> findAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        if(bookingList.isEmpty()) throw new NoRecordFoundException("Booking Record Not Found");
        return bookingList.stream().map(x -> bookingRequestMapper
                .toBookingRequest(x)).collect(Collectors.toList());
    }
}
