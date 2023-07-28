package com.javainterns.bookingroom;

import com.javainterns.bookingroom.model.Booking;
import com.javainterns.bookingroom.model.Room;
import com.javainterns.bookingroom.model.User;
import com.javainterns.bookingroom.repository.UserRepository;
import com.javainterns.bookingroom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class BookingroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingroomApplication.class, args);

	}

}
