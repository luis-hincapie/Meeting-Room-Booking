package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

  @Email(message = "email")
  @NotBlank
  private String email;

  @NotBlank
  private String username;

  @NotBlank
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
}
