package com.javainterns.bookingroom.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ClientRequest implements Serializable {
    @NotBlank(message = "Name must not be blank")
    @Schema(example = "Room 1")
    @Size(max = 100,message = "Name size must be between 0 and 100")
    private String name;
    @Email(message = "Email invalid format")
    @NotBlank(message = "Email must not be blank")
    @Size(max = 100,message = "Email size must be between 0 and 100")
    @Schema(example = "example@email.com")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientRequest(@NotNull String name, @NotNull String email) {
        this.name = name;
        this.email = email;
    }

    public ClientRequest() {

    }
}
