package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
}
