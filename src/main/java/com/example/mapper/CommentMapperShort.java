package com.example.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentMapperShort {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String name;
    private String surname;
    private String content;
    private String articleId;
    private String title;
    private Integer replyId;

    public CommentMapperShort(Integer id, LocalDateTime createdDate, LocalDateTime updateDate, Integer profileId, String name, String surname) {
        this.id = id;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.profileId = profileId;
        this.name = name;
        this.surname = surname;
    }
}
