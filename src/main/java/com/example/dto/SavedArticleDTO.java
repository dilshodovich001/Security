package com.example.dto;

import com.example.dto.article.ArticleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Getter@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavedArticleDTO {

    private Integer id;
    @Positive
    private Integer profileId;
    private ProfileDTO profile;
    @NotBlank
    private String articleId;
    private ArticleDTO article;
    private LocalDateTime createdDate;
}
