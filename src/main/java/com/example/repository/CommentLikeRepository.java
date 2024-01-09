package com.example.repository;

import com.example.entity.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {
    CommentLikeEntity findByProfileIdAndCommentId(Integer profileId,Integer commentId);



@Transactional
@Modifying
@Query("delete  from CommentLikeEntity c where c.profileId =?1 and  c.commentId = ?2")
    int deleted(Integer profileId, Integer commentId);
}
