package com.javainterns.bookingroom.model.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class BookingRequest {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long roomId;
    @NotNull
    @FutureOrPresent(message = "Date error")
    private LocalDate date;
    @NotEmpty
    @Size(max = 23)
    @PositiveOrZero
    @Schema(example = "5")
    private Integer startTime;
    @NotEmpty
    @Size(max = 23)
    @PositiveOrZero
    @Schema(example = "20")
    private Integer endTime;

    public BookingRequest(){}

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
