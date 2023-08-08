package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.HoursOfOperationNotAvailableException;
import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.RoomAlreadyBooked;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    public ProblemDetail handleNoRecordFoundException(NoRecordFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date());
        problemDetail.setTitle("Record not found");
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }

    @ExceptionHandler(StartTimeIsGreaterThanEndTime.class)
    @ResponseBody
    public ProblemDetail startTimeIsGreaterThanEndTime(StartTimeIsGreaterThanEndTime exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date());
        problemDetail.setTitle("Start time greater than time");
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }

    @ExceptionHandler(RoomAlreadyBooked.class)
    @ResponseBody
    public ProblemDetail handleRoomAlreadyBooked(RoomAlreadyBooked exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date());
        problemDetail.setTitle("Room already booked at this time");
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }

    @ExceptionHandler(HoursOfOperationNotAvailableException.class)
    @ResponseBody
    public ProblemDetail handleHoursOfOperationNotAvailableException(HoursOfOperationNotAvailableException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date());
        problemDetail.setTitle("Room isn't open");
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Data validation error");
        problemDetail.setProperty("timestamp", new Date().toString());
        problemDetail.setTitle("Argument not Valid");
        problemDetail.setProperty("errors", exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        problemDetail.setType(URI.create(""));
        return problemDetail;
    }
}
