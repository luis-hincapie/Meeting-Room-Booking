package com.javainterns.bookingroom.utils;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class TimeValidator {
    private static final String RANGE_TIME_ERROR =
            "start-time.could.not.be.greater.than.finish-time";
    private final Messages messages;

    public TimeValidator(Messages messages) {
        this.messages = messages;
    }

    public void isValidTimeRange(LocalTime endTime, LocalTime startTime) {
        if (endTime.isBefore(startTime))
            throw new StartTimeIsGreaterThanEndTime(messages.get(RANGE_TIME_ERROR));
    }

    public void bookingHourValidation(Booking booking, List<Booking> booked) {
        if (!booked
                .stream()
                .allMatch(x ->
                        (x.getStartTime().isAfter(booking.getEndTime())) ||
                                (x.getEndTime().isBefore(booking.getStartTime()))
                )) throw new RoomAlreadyBooked("Room is already booked");
    }

    public void bookingRoomHourValidation(Booking booking, Room room) {

        if (booking.getStartTime().isBefore(room.getStartTime()) &&
                booking.getEndTime().isAfter(room.getFinishTime()))
            throw new HoursOfOperationNotAvailableException("The Room isn't open at this time");
    }
}
