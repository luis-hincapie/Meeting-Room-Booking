package com.javainterns.bookingroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javainterns.bookingroom.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Email(message = "email")
    @NotBlank
    @Schema(example = "user@mail.com")
    @Size(max = 100)
    private String email;

    @NotBlank
    @Schema(example = "user")
    @Size(max = 100)
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(example = "1234")
    @Size(max = 100)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Role> roles;
}
