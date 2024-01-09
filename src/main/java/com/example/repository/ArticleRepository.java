package com.example.repository;

import com.example.dto.article.ArticleUpdateDTO;
import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import com.example.mapper.IArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    Optional<ArticleEntity> findByImageId(String id);


    @Transactional
    @Modifying
    @Query("update ArticleEntity a set a.title = ?2 ," +
            "a.description = ?3," +
            "a.content = ?4," +
            "a.sharedCount = ?5," +
            "a.imageId = ?6," +
            "a.regionId = ?7," +
            "a.categoryId = ?8" +
            " where a.id = ?1")
    int update(String id,
               String title,
               String description,
               String content,
               Integer sharedCount,
               String imageId,
               Integer regionId,
               Integer categoryId);


    @Transactional
    @Modifying
    @Query("update ArticleEntity a set a.visible = false where a.id = ?1")
    int deleteArticle(String id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity  set status = ?2 ,publishedDate = LOCAL DATETIME where id =?1")
    int status(String id, ArticleStatus status);

    @Query(value = "select * from article where status =?1 order by created_date desc limit 5", nativeQuery = true)
    List<ArticleEntity> lastFive(String status);

    @Query(value = "select * from article where status =?1 order by created_date desc limit 3", nativeQuery = true)
    List<ArticleEntity> lastThree(String status);


    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status=?1 and  a.id not in(?2) order by a.created_date desc  limit 8", nativeQuery = true)
    List<IArticleShortInfoMapper> getTop8Article(String name, List<String> idList);

    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED'  order by view_count desc  limit 4", nativeQuery = true)
    List<IArticleShortInfoMapper> mostReads();

    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED' and  a.id not in(?1) order by a.created_date desc  limit 4", nativeQuery = true)
    List<IArticleShortInfoMapper> getLastFour(String id);

    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED'  and  a.region_id = ?1 order by created_date desc  limit 5", nativeQuery = true)
    List<IArticleShortInfoMapper> getByRegionId(Integer id);


    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED'  and  a.region_id = ?1 order by created_date desc ", nativeQuery = true)
    Page<IArticleShortInfoMapper> findAllByKey(Pageable paging, Integer id);

    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED'  and  a.category_id = ?1 order by created_date desc limit 5", nativeQuery = true)
    List<IArticleShortInfoMapper> getByCategoryId(Integer id);

    @Query(value = " select a.id as id, a.title as title, a.description as description,a.image_id as imageId," +
            " a.published_date as publishedDate from article a " +
            "where a.status='PUBLISHED'  and  a.category_id = ?1 order by created_date desc ", nativeQuery = true)
    Page<IArticleShortInfoMapper> findAllByCategoryKey(Pageable paging, Integer id);


    @Transactional
    @Modifying
    @Query("update ArticleEntity a set a.viewCount = a.viewCount + 1 where a.id = ?1")
    void viewCount(String id);

    @Query("select a.viewCount from ArticleEntity a where a.id = ?1")
    int viewCountReturn(String id);

    @Modifying
    @Transactional
    @Query("update ArticleEntity  a set a.sharedCount = a.sharedCount + 1 where a.id = ?1")
    void shareCount(String articleId);
    @Query("select a.sharedCount from ArticleEntity a where a.id = ?1")
    int shareCountReturn(String articleId);
}
