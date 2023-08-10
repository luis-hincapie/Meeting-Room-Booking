package com.javainterns.bookingroom.service;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.model.dto.BookingRequest;
import com.javainterns.bookingroom.model.mapper.BookingRequestMapper;
import com.javainterns.bookingroom.model.mapper.RoomRequestMapper;
import com.javainterns.bookingroom.repository.BookingRepository;
import com.javainterns.bookingroom.utils.Messages;
import com.javainterns.bookingroom.utils.TimeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingRequestMapper bookingRequestMapper;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    TimeValidation timeValidation;

    @Autowired
    RoomRequestMapper roomRequestMapper;

    @Autowired
    private Messages messages;

    @Override
    public BookingRequest create(BookingRequest bookingRequest, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Booking booking = bookingRequestMapper.toBooking(bookingRequest);
        if(!timeValidation.isValidTimeRange(booking.getEndTime(), booking.getStartTime()))
            throw new StartTimeIsGreaterThanEndTime("Start time greater than time");
        Room room = roomService.findRoom(bookingRequest.getRoomId());
        List<Booking> bookingList = bookingRepository.findBookingsByRoomIdAndDate(
                booking.getDate(),
                room.getId()
        );
        if (!timeValidation.bookingRoomHourValidation(booking, room))
            throw new HoursOfOperationNotAvailableException("The Room isn't open at this time");
        if (!timeValidation.bookingHourValidation(booking, bookingList))
            throw new RoomAlreadyBooked(room.getId().toString());
        booking.setUser(user);
        booking.setRoom(room);
        return bookingRequestMapper.toBookingRequest(
                bookingRepository.save(booking)
        );
    }

    @Override
    public Boolean delete(Long id) {
        if (!bookingRepository.existsById(id)) throw new NoRecordFoundException(
                messages.get("booking.record.not.found")
        );
        bookingRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteByUser(Long id, Principal principal) {
;
    bookingRepository.deleteById(
            bookingRepository.findByIdAndUser_Username(id, principal.getName())
                    .orElseThrow(()->new NoRecordFoundException(messages.get("booking.record.not.found")))
                    .getId());
        return true;
    }

    @Override
    public BookingRequest findById(Long id, Principal principal) {
        return bookingRequestMapper.toBookingRequest(
                bookingRepository
                        .findByIdAndUser_Username(id, principal.getName())
                        .orElseThrow(() ->
                                new NoRecordFoundException("Booking Records not found")
                        )
        );
    }

    @Override
    public List<BookingRequest> findAll() {
        return bookingRepository
                .findAll()
                .stream()
                .map(x -> bookingRequestMapper.toBookingRequest(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingRequest> findBookingsByUsername(String username) {
        return bookingRepository.findByUser_Username(username)
                .stream()
                .map(booking -> bookingRequestMapper.toBookingRequest(booking))
                .toList();
    }

}
