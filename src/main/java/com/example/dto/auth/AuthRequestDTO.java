package com.example.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthRequestDTO {
    @NotBlank
    @Size(min = 4, message = "Phone is required")
    private String phone;
    @NotBlank
    @Size(min = 4, message = "Password is required")
    private String password;
}
