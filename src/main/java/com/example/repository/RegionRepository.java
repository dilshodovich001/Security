package com.example.repository;

import com.example.entity.RegionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    RegionEntity findByKey(String key);

    List<RegionEntity> findAllByVisibleTrue();


    @Transactional
    @Modifying
    @Query("UPDATE RegionEntity set key = ?1 , nameEn = ?2,nameRu = ?3,nameUz = ?4, visible = ?5 where id = ?6")
    int update(String key, String nameEng, String nameRu, String nameUz, Boolean visible, Integer id);


    RegionEntity findAllByKey(String key);

}
