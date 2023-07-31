package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
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
        return new ResponseEntity<String>("startTime could not be greater than finishTime", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
