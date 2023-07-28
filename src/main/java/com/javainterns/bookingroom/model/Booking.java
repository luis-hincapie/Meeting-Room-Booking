package com.javainterns.bookingroom.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Room.class)
    private Room room;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private LocalTime startTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private LocalTime endTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Booking(Room room, User user, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }
}
