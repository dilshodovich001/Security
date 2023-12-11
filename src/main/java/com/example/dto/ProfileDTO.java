package com.example.dto;


import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    private String name;

    private String surname;

    private String phone;

    private String password;

    private String email;


    private ProfileRole role;

    private ProfileStatus status;
    private LocalDateTime createdDate;
    private Boolean visible;
}