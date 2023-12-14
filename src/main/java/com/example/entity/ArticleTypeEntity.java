package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "article_type")
public class ArticleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String key;
    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_eng")
    private String nameEn;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
