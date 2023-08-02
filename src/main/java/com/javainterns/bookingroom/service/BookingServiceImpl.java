package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Client;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.model.mapper.ClientRequestMapper;
import com.javainterns.bookingroom.repository.BookingRepository;
import com.javainterns.bookingroom.utils.Messages;
import com.javainterns.bookingroom.utils.TimeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    TimeValidation timeValidation;
    @Autowired
    private Messages messages;
    @Autowired
    private ClientRequestMapper clientRequestMapper;

    @Override
    public BookingRequest create(BookingRequest bookingRequest) {
        Client client = clientRequestMapper.toClient(clientService.findById(bookingRequest.getUserId()));
        Booking booking = bookingRequestMapper.toBooking(bookingRequest);
        timeValidation.isValidTimeRange(booking.getEndTime(), booking.getStartTime());
        Room room = roomService.findRoom(bookingRequest.getRoomId());
        List<Booking> bookingList = bookingRepository.findBookingsByRoomIdAndDate(booking.getDate(),room.getId());
        if (!timeValidation.bookingHourValidation(booking,bookingList)) throw new RoomAlreadyBooked(room.getId().toString());
        if (!timeValidation.bookingRoomHourValidation(booking, room)) throw new HoursOfOperationNotAvailableException("The Room isn't open at this time");
        booking.setUser(client);
        booking.setRoom(room);
        return bookingRequestMapper.toBookingRequest(bookingRepository.save(booking));
    }

    @Override
    public Boolean delete(Long id) {
        if (!bookingRepository.existsById(id)) throw new NoRecordFoundException(messages.get("booking.record.not.found"));
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
