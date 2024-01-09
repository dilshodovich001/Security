package com.example.dto;

import com.example.dto.article.ArticleDTO;
import com.example.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private ProfileEntity profile;
    @NotBlank
    private String content;
    private String articleId;
    private ArticleDTO article;
    private Boolean visible;
    private Integer replyId;
    private CommentDTO reply;


}
