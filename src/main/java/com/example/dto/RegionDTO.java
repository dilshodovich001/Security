package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private String key;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
    private LocalDate created_date;
}
