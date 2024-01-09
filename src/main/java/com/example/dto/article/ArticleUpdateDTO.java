package com.example.dto.article;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleUpdateDTO {
//title,description,content,shared_count,image_id, region_id,category_id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
    @NotBlank
    private String imageId;
    @Positive
    private Integer regionId;
    @Positive
    private Integer categoryId;
    private Integer sharedCount;


}