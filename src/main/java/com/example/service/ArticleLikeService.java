package com.example.service;

import com.example.dto.article.ArticleLikeDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.enums.ArticleLikeStatus;
import com.example.repository.ArticleLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeService {
    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public void create(ArticleLikeDTO articleLikeDTO) {
        ArticleLikeEntity likeEntity = get(articleLikeDTO);
        if (likeEntity != null) {
            updateStatus(articleLikeDTO);
        }
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setProfileId(articleLikeDTO.getProfileId());
        entity.setArticleId(articleLikeDTO.getArticleId());
        entity.setStatus(articleLikeDTO.getStatus());
        articleLikeRepository.save(entity);
    }

    public void updateStatus(ArticleLikeDTO articleLikeDTO) {
        ArticleLikeEntity likeEntity = get(articleLikeDTO);
      if (likeEntity.getStatus().equals(articleLikeDTO.getStatus())){
          deleteLike(articleLikeDTO);
          return;
      }else if (likeEntity.getStatus().equals(ArticleLikeStatus.LIKE)) {
          articleLikeDTO.setStatus(ArticleLikeStatus.DISLIKE);
      }else if (likeEntity.getStatus().equals(ArticleLikeStatus.DISLIKE)){
          articleLikeDTO.setStatus(ArticleLikeStatus.LIKE);
      }
      likeEntity.setStatus(articleLikeDTO.getStatus());
      articleLikeRepository.save(likeEntity);
    }

    public void deleteLike(ArticleLikeDTO dto) {
        articleLikeRepository.deleted(dto.getProfileId(), dto.getArticleId());
    }

    public ArticleLikeEntity get(ArticleLikeDTO dto){
        return articleLikeRepository.
                findByProfileIdAndArticleId(
                        dto.getProfileId(),
                        dto.getArticleId());

    }
}
