package com.javainterns.bookingroom.exceptions;

public class HoursOfOperationNotAvailableException extends RuntimeException{
    public HoursOfOperationNotAvailableException(String message){
        super(message);
    }
}
