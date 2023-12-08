package com.example.dto.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
