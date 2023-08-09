package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotNull(message = "Room Id must not be null")
  @Range(min = 0, message = "Room Id must be greater than 0")
  private Long roomId;

  @NotNull(message = "Date must not be null")
  @FutureOrPresent(message = "Date must be today or a future date")
  private LocalDate date;

  @NotNull(message = "Start Time must not be null")
  private LocalTime startTime;

  @NotNull(message = "End Time must not be null")
  private LocalTime endTime;
}
