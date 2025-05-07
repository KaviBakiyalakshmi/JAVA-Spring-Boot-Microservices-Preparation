package com.trading;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateTraderDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    // Getters and Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UpdateTraderDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UpdateTraderDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
