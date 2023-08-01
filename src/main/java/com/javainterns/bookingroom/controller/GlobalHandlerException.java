package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNoRecordFoundException(NoRecordFoundException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StartTimeIsGreaterThanEndTime.class)
    @ResponseBody
    public ResponseEntity<String> stratTiemIsGreaterThanEndTime(StartTimeIsGreaterThanEndTime exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(RoomAlreadyBooked.class)
    @ResponseBody
    public ResponseEntity<String> handleRoomAlreadyBooked(RoomAlreadyBooked exception){
        return new ResponseEntity<String>(exception.getMessage()+" Room already booked at this time", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HoursOfOperationNotAvailableException.class)
    @ResponseBody
    public ResponseEntity<String> handleHoursOfOperationNotAvailableException(HoursOfOperationNotAvailableException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
