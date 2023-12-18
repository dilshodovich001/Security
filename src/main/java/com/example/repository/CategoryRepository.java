package com.example.repository;

import com.example.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity , Integer> {
    CategoryEntity findByKey(String key);

    @Transactional
    @Modifying
    @Query("UPDATE CategoryEntity set key = ?1 , nameEn = ?2,nameRu = ?3,nameUz = ?4, visible = ?5 where id = ?6")
    int update(String key, String nameEng, String nameRu, String nameUz, Boolean visible, Integer id);

    @Transactional
    @Modifying
    @Query("update CategoryEntity c set c.visible = false where c.id =?1 ")
    int updateId(Integer id);
}
