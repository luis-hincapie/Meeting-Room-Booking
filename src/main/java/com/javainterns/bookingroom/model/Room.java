package com.javainterns.bookingroom.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    private long id;

    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String location;
    @Column(nullable=false)
    private int capacity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date finishTime;

    @OneToMany(targetEntity = Booking.class)
    private List<Booking> bookingList;

    public Room() {
    }

    public Room(String name, String location, int capacity, Date startTime, Date finishTime) {

        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(String hola) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
