package com.example.dto;

import com.example.enums.LangEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    private Long id;
    private String key;
    private LangEnum lang;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private String name;
    private LocalDateTime createdDate;
    private String visible;

}
