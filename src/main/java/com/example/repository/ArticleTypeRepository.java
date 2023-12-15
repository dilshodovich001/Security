package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity ,Long> {
    ArticleTypeEntity findByKey(String key);

    @Transactional
    @Modifying
    @Query("UPDATE ArticleTypeEntity set key = ?1 , nameEn = ?2,nameRu = ?3,nameUz = ?4 where id = ?5")
    int update(String key, String nameEn, String nameRu, String nameUz, Long id);
}

