package com.javainterns.bookingroom.model.dto;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javainterns.bookingroom.exceptions.InvalidDataException;
import com.javainterns.bookingroom.exceptions.StartTimeIsGreaterThanEndTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class BookingRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull(message="User Id must not be null")
    @Range(min = 0, message = "User Id must be greater than 0")
    private Long userId;
    @NotNull(message="Room Id must not be null")
    @Range(min = 0, message = "Room Id must be greater than 0")
    private Long roomId;
    @NotNull(message="Date must not be null")
    @FutureOrPresent(message = "Date must be today or future")
    private LocalDate date;
    @NotNull(message="Start Time must not be null")
    @Range(min = 0,max = 23, message = "Start time must be between 0 and 23")
    @Schema(example = "5")
    private Integer startTime;
    @NotNull(message="End Time must not be null")
    @Range(min = 0,max = 23, message = "End time must be between 0 and 23")
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

    @JsonIgnore
    @AssertTrue()
    public boolean isValidTimeRange(){
        if(endTime<=startTime || (endTime==0 && startTime !=0))
            throw new StartTimeIsGreaterThanEndTime("startTime could not be greater than finishTime");
        //if(date.isBefore(LocalDate.now()))
            //throw new InvalidDataException("Date must be today or future");
        return true;
    }

}
