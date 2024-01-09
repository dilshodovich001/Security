package com.example.dto.article;

import com.example.dto.attach.AttachShortInfoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleShortInfoDTO {
private String id;
private String title;
private String description;
private AttachShortInfoDTO image;
private LocalDateTime publishedDate;

}
