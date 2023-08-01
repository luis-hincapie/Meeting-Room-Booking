package com.javainterns.bookingroom.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClientRequest {
    @NotBlank
    @Schema(example = "Room 1")
    @Size(max = 100)
    private String name;
    @Email
    @NotBlank
    @Schema(example = "example@email.com")
    @Size(max = 100)
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
