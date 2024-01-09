package com.example.repository;

import com.example.entity.ArticleLikeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity , Integer> {
    ArticleLikeEntity findByProfileIdAndArticleId(Integer profileId, String article);



    @Transactional
    @Modifying
    @Query("delete from ArticleLikeEntity a where a.profileId = ?1 and a.articleId = ?2")
    void deleted(Integer profileId, String articleId);
}
