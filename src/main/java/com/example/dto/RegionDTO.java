package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    @NotBlank
    @Size(min = 5,message = "Key is required")
    private String key;
    @NotBlank
    @Size(min = 5,message = "Name uzbek is required")
    private String nameUz;
    @NotBlank
    @Size(min = 5,message = "Name russian is required")
    private String nameRu;
    @NotBlank
    @Size(min = 5,message = "Name english is required")
    private String nameEng;
    private Boolean visible;
    private LocalDateTime createdDate;

    @Size(min = 5,message = "Name is required")
    private String name;

}
