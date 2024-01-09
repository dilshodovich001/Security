package com.example.repository;

import com.example.entity.CommentEntity;
import com.example.mapper.CommentMapperShort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update CommentEntity c set c.content = ?1 , c.updateDate = local datetime  where c.articleId = ?2 ")
    int update(String content, String articleId);


    @Modifying
    @Transactional
    @Query("update CommentEntity as c set c.visible=false where c.id=?1")
    void deleteAdmin(Integer commentId);


    @Modifying
    @Transactional
    @Query("update CommentEntity as c set c.visible = false where c.id=?1")
    void deleteOwn(Integer commentId);


    @Query("select new com.example.mapper.CommentMapperShort(c.id,c.createdDate,c.updateDate,c.profileId , c.profile.name,c.profile.surname,c.content,c.article.id,c.article.title,c.replyId) from CommentEntity  c")

    Page<CommentMapperShort> getPageList(Pageable paging);


    @Query("select new com.example.mapper.CommentMapperShort(c.id,c.createdDate," +
            "c.updateDate,c.profileId , c.profile.name" +
            ",c.profile.surname) from CommentEntity c where   c.id = ?1 and c.replyId  is  not null")
    List<CommentMapperShort> repliedList(Integer commentId);
}
