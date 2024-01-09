package com.example.dto;

import com.example.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ArticleDTO {
    private UUID id;
    private String title;
    private String description;
    private String content;
    private Long shared_count;
    private String image_id;
    private Integer region_id;
    private Integer category_id;
    private Long moderator_id;
    private Long publisher_id;
    private StatusEnum status; //(Published,NotPublished)
    private LocalDate created_date;
    private LocalDate published_date;
    private Boolean visible;
    private Long view_count;
}
