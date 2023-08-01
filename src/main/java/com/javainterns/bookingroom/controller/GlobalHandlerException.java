package com.javainterns.bookingroom.controller;

import com.javainterns.bookingroom.exceptions.NoRecordFoundException;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException{
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("error", "Argument Not Valid");
        List<String> errors = new java.util.ArrayList<>(ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList());
        response.put("message", errors.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(UnexpectedTypeException ex) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().toString());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        response.put("error", "Argument Not Valid");
        response.put("message",ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
