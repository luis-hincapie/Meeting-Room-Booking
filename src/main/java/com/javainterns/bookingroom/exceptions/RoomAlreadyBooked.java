package com.javainterns.bookingroom.exceptions;

public class RoomAlreadyBooked extends RuntimeException {

  public RoomAlreadyBooked(String message) {
    super(message);
  }
}
