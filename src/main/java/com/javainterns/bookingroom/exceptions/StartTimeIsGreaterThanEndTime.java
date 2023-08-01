package com.javainterns.bookingroom.exceptions;

public class StartTimeIsGreaterThanEndTime extends RuntimeException{
    public StartTimeIsGreaterThanEndTime(String message) {
        super(message);
    }
}
