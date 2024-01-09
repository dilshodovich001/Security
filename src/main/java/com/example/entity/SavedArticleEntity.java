package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "saved_article")
public class SavedArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "profile_id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;
    @Column(name = "article_id" )
    private String articleId;
    @JoinColumn(name = "article_id",updatable = false,insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ArticleEntity article;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

}
