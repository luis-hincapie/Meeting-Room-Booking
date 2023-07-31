package com.javainterns.bookingroom.model;

import jakarta.persistence.*;


import java.time.LocalTime;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String location;
    @Column(nullable=false)
    private Integer capacity;

    @Column(nullable=false)
    private Integer startTime;

    @Column(nullable=false)
    private Integer finishTime;

    @Column(nullable=false)
    private boolean isActive;

    @OneToMany(targetEntity = Booking.class)
    private List<Booking> bookingList;

    public Room() {
    }

    public Room(String name, String location, Integer capacity, Integer startTime, Integer finishTime) {

        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
