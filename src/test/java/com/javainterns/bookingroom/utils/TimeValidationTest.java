package com.javainterns.bookingroom.utils;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class TimeValidationTest {

    Booking booking1 = new Booking(null, null, null, LocalTime.of(12, 00, 00), LocalTime.of(13, 00, 000), null);
    Booking booking2 = new Booking(null, null, null, LocalTime.of(12, 00, 00), LocalTime.of(13, 00, 000), null);
    Booking booking3 = new Booking(null, null, null, LocalTime.of(12, 00, 00), LocalTime.of(13, 00, 000), null);
    Room room = new Room(null,null,null,null, null, null,true,null);

    @Autowired
    TimeValidator timeValidation;

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({"00:00,01:00", "01:28,02:30", "23:00,23:59", "00:00,23:59", "11:00,23:00"})
    void falseIfStartTimeIsAfterEndTime(LocalTime endTime, LocalTime startTime) {
        assertThrows(StartTimeIsGreaterThanEndTime.class,()->timeValidation.isValidTimeRange(endTime, startTime));
    }

    @ParameterizedTest
    @CsvSource({"01:00,00:00", "03:28,02:30", "23:59,23:00", "23:59,00:00", "23:00,11:00"})
    void trueIfStartTimeIsBeforeEndTime(LocalTime endTime, LocalTime startTime) {
        assertDoesNotThrow(()->timeValidation.isValidTimeRange(endTime, startTime));
    }

    @ParameterizedTest
    @CsvSource({"02:00,03:00,05:00,10:00,03:01,04:59", "02:00,03:00,08:00,10:00,04:00,05:00", "02:00,03:00,05:00,10:00,00:00,01:59", "02:00,03:00,05:00,10:00,10:01,11:00","02:00,03:00,05:00:01,10:00,03:00:01,05:00"})
    void roomNotBookedAtThisTime(LocalTime startTime1, LocalTime endTime1, LocalTime startTime2, LocalTime endTime2,LocalTime startTime3, LocalTime endTime3) {
        booking1.setStartTime(startTime1);
        booking1.setEndTime(endTime1);
        booking2.setStartTime(startTime2);
        booking2.setEndTime(endTime2);
        booking3.setStartTime(startTime3);
        booking3.setEndTime(endTime3);
        assertDoesNotThrow(()->timeValidation.bookingHourValidation(booking3, List.of(booking1,booking2)));
    }

    @ParameterizedTest
    @CsvSource({"02:00,03:00,05:00,10:00,03:00,04:59", "02:00,03:00,08:00,10:00,02:30,05:00", "02:00,03:00,05:00,10:00,00:00,02:01", "02:00,03:00,05:00,10:00,09:59,11:00","02:00,03:00,05:00:00,10:00,03:00:00,05:00"})
    void roomAlreadyBookedAtThisTime(LocalTime startTime1, LocalTime endTime1, LocalTime startTime2, LocalTime endTime2,LocalTime startTime3, LocalTime endTime3) {
        booking1.setStartTime(startTime1);
        booking1.setEndTime(endTime1);
        booking2.setStartTime(startTime2);
        booking2.setEndTime(endTime2);
        booking3.setStartTime(startTime3);
        booking3.setEndTime(endTime3);
        assertThrows(RoomAlreadyBooked.class,()->timeValidation.bookingHourValidation(booking3, List.of(booking1,booking2)));
    }

    @ParameterizedTest
    @CsvSource({"01:00,10:00,01:00:01,03:00", "03:28,05:30,04:39,05:29:59"})
    void testIsValidTimeRange(LocalTime roomStartTime, LocalTime roomEndTime, LocalTime startTime, LocalTime endTime) {
        room.setStartTime(roomStartTime);
        room.setFinishTime(roomEndTime);
        booking1.setStartTime(startTime);
        booking1.setEndTime(endTime);
        assertDoesNotThrow(()->timeValidation.bookingRoomHourValidation(booking1,room));
    }

    @ParameterizedTest
    @CsvSource({"01:00,10:00,00:00:00,01:01", "03:28,05:30,04:39,05:30:00"})
    void roomClosedAtThisTime(LocalTime roomStartTime, LocalTime roomEndTime, LocalTime startTime, LocalTime endTime) {
        room.setStartTime(roomStartTime);
        room.setFinishTime(roomEndTime);
        booking1.setStartTime(startTime);
        booking1.setEndTime(endTime);
        assertThrows(HoursOfOperationNotAvailableException.class,()->timeValidation.bookingRoomHourValidation(booking1,room));
    }
}