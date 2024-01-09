package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String key;

    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_ru")

    private String nameRu;
    @Column(name = "name_eng")
    private String nameEn;
    @Column
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

}
