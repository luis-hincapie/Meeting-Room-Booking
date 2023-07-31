package com.javainterns.bookingroom.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class RoomRequest {

    @NotBlank
    @Schema(example = "Room 1")
    @Size(max = 100)
    private String name;


    @NotBlank
    @Size(max = 100)
    @Schema(example = "Medellín")
    private String location;

    @NotEmpty
    @Size(max = 999)
    @Positive
    @Schema(example = "3")
    private Integer capacity;

    @NotEmpty
    @Size(max = 23)
    @PositiveOrZero
    @Schema(example = "5")
    private Integer startTime;

    @NotEmpty
    @Size(max = 23)
    @PositiveOrZero
    @Schema(example = "20")
    private Integer finishTime;

    private Boolean isActive;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public RoomRequest(String name, String location, Integer capacity, Integer startTime, Integer finishTime, Boolean isActive) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.isActive = isActive;
    }
}
