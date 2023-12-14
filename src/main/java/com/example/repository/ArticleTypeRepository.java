package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity ,Long> {
    ArticleTypeEntity findByKey(String key);
}

