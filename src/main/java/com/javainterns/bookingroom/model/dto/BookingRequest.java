package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotNull(message = "User Id must not be null")
  @Range(min = 0, message = "User Id must be greater than 0")
  private Long userId;

  @NotNull(message = "Room Id must not be null")
  @Range(min = 0, message = "Room Id must be greater than 0")
  private Long roomId;

  @NotNull(message = "Date must not be null")
  @FutureOrPresent(message = "Date must be today or a future date")
  private LocalDate date;

  @NotNull(message = "Start Time must not be null")
  @Range(min = 0, max = 23, message = "Start time must be between 0 and 23")
  @Schema(example = "5")
  private Integer startTime;

  @NotNull(message = "End Time must not be null")
  @Range(min = 0, max = 23, message = "End time must be between 0 and 23")
  @Schema(example = "20")
  private Integer endTime;
}
