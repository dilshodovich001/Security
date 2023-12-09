package com.example.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegistrationDTO {

    @NotBlank
    @Size(min = 3, message = "Name is required")
    private String name;

    @NotBlank
    @Size(min = 3, message = "Surname is required")
    private String surname;

    @NotBlank
    @Size(min = 4, message = "Phone is wrong")
    private String phone;

    @NotBlank
    @Size(min = 4, message = "Password is wrong")
    private String password;

    @NotBlank
    @Email
    private String email;


}
