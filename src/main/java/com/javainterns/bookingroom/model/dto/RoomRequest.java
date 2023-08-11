package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;
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

  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  @Schema(example = "08:00")
  private LocalTime startTime;

  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  @Schema(example = "18:00")
  private LocalTime finishTime;

  private Boolean isActive;
}
