package com.example.dto.article;


import com.example.dto.CategoryDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegionDTO;
import com.example.dto.attach.AttachDTO;
import com.example.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private ArticleStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Integer viewCount;
    private Integer sharedCount;

    private String imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;

    private AttachDTO image;
    private RegionDTO region;
    private CategoryDTO category;
    private ProfileDTO moderator;
    private ProfileDTO publisher;
}