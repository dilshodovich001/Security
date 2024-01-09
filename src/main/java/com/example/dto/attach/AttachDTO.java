package com.example.dto.attach;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AttachDTO {
    private String id;
    @NotBlank
    @Size(min = 32,message = "Url is required")
    private String url;
    @NotBlank
    @Size(min = 32,message = "Original name is required")
    private String originalName;
    @NotBlank
    @Size(min = 32,message = "Path name is required")
    private String path;
    private Long size;
    private LocalDateTime createdDate;

    public AttachDTO(String url) {
        this.url = url;
    }
}
