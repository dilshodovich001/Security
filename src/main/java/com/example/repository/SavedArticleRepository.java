package com.example.repository;

import com.example.entity.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity , Integer> {

@Transactional
@Modifying   //na vsyaki sluci kere bob qosa
//@Query("delete from SavedArticleEntity where profileId = ?1 and articleId = ?2")
    int deleteByProfileIdAndArticleId(Integer profileId, String articleId);
}
