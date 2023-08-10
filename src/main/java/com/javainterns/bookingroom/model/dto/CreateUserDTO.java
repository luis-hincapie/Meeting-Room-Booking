package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

  @Email(message = "email")
  @Schema(example = "user@mail.com")
  @Size(max = 100)
  @NotBlank
  private String email;

  @NotBlank
  @Schema(example = "user")
  @Size(max = 100)
  private String username;

  @NotBlank
  @Schema(example = "1234")
  @Size(max = 255)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
}
