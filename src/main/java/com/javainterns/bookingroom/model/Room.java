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
    private Date start_time;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date finish_time;

    @OneToMany(targetEntity = Booking.class)
    private List<Booking> bookingList;

    public Room(String name, String location, int capacity, Date start_time, Date finish_time) {

        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.start_time = start_time;
        this.finish_time = finish_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
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

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }
}
