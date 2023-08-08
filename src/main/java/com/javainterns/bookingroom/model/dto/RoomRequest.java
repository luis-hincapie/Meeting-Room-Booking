package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @NotBlank(message = "Name must not be blank")
  @Schema(example = "Room 1")
  @Size(max = 100, message = "Name size must be between 0 and 100")
  private String name;

  @NotBlank(message = "Location must not be blank")
  @Size(max = 100, message = "Location size must be between 0 and 100")
  @Schema(example = "Medellín")
  private String location;

  @Range(min = 1, max = 999, message = "Capacity must be between 0 and 999")
  @Schema(example = "3")
  private Integer capacity;

  @Range(min = 0, max = 23, message = "Start time must be between 0 and 23")
  @Schema(example = "5")
  private Integer startTime;

  @Range(min = 0, max = 23, message = "End time must be between 0 and 23")
  @Schema(example = "20")
  private Integer finishTime;

  private Boolean isActive;
}
