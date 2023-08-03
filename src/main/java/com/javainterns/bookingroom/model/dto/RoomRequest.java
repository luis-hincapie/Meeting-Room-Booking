package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class RoomRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Schema(example = "Room 1")
    @Size(max = 100,message = "Name size must be between 0 and 100")
    private String name;


    @NotBlank(message = "Location must not be blank")
    @Size(max = 100, message = "Location size must be between 0 and 100")
    @Schema(example = "Medellín")
    private String location;

    @Range(min =1,max = 999, message= "Capacity must be between 0 and 999")
    @Schema(example = "3")
    private Integer capacity;

    @Range(min=0,max = 23,message = "Start time must be between 0 and 23")
    @Schema(example = "5")
    private Integer startTime;

    @Range(min=0,max = 23,message = "End time must be between 0 and 23")
    @Schema(example = "20")
    private Integer finishTime;

    private Boolean isActive;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public RoomRequest() {
    }

    public RoomRequest(String name, String location, Integer capacity, Integer startTime, Integer finishTime, Boolean isActive) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.isActive = isActive;
    }

    public RoomRequest(Long id, String name, String location, Integer capacity, Integer startTime, Integer finishTime, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.isActive = isActive;
    }
}
