package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoRecordFoundException(NoRecordFoundException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.NOT_FOUND.toString());
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Map<String, String>> handleRoomAlreadyBooked(RoomAlreadyBooked exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.CONFLICT.toString());
        response.put("message", exception.getMessage() + " Room already booked at this time");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HoursOfOperationNotAvailableException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleHoursOfOperationNotAvailableException(HoursOfOperationNotAvailableException exception) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.CONFLICT.toString());
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setProperty("timestamp", new Date().toString());
        problemDetail.setTitle("Argument not Valid");
        problemDetail.setProperty("errors", new java.util.ArrayList<>(exception.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList()));
        return problemDetail;
    }
}
