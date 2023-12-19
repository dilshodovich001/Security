package com.example.entity;

import com.example.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description",
            columnDefinition = "TEXT")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "shared_count")
    private Long shared_count;
    @Column(name = "image_id")
    private String image_id;
    @Column(name = "region_id")
    private Integer region_id;
    @Column(name = "category_id")
    private Integer category_id;
    @Column(name = "moderator_id")
    private Long moderator_id;
    @Column(name = "publisher_id")
    private Long publisher_id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.NOTPUBLISHED;//(Published,NotPublished)
    @Column(name = "created_date")
    private LocalDate created_date;
    @Column(name = "published_date")
    private LocalDate published_date;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "view_count")
    private Long view_count;
}
