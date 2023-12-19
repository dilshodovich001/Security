package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity,Integer> {

    Optional<RegionEntity> findByKey(String key);

    @Transactional
    @Modifying
    @Query("update RegionEntity as r set r.key = ?2,r.name_uz = ?3,r.name_ru = ?4,r.name_en =?5 where r.id = ?1")
    int updateById(Integer id, String key, String name_uz, String name_ru, String name_en);

    @Transactional
    @Modifying
    @Query("delete RegionEntity as r where r.id = ?1")
    int delete(Integer id);

    List<RegionEntity> findAllByVisibleTrue();
}
