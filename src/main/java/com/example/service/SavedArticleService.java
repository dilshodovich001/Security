package com.example.service;

import com.example.dto.SavedArticleDTO;
import com.example.entity.SavedArticleEntity;
import com.example.repository.SavedArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SavedArticleService {

    @Autowired private ArticleService articleService;

    @Autowired private SavedArticleRepository savedArticleRepository;
        public SavedArticleDTO create(SavedArticleDTO dto) {
            SavedArticleEntity entity = toEntity(dto);
                savedArticleRepository.save(entity);
                dto.setId(entity.getId());
            return dto;
        }

        private SavedArticleEntity toEntity(SavedArticleDTO dto) {
            SavedArticleEntity entity = new SavedArticleEntity();
            entity.setArticleId(dto.getArticleId());
            entity.setProfileId(dto.getProfileId());
            return entity;
        }

    public Boolean delete(SavedArticleDTO dto) {
        int i = savedArticleRepository.deleteByProfileIdAndArticleId(dto.getProfileId(), dto.getArticleId());
        return i != 0;
    }

//    public List<SavedArticleDTO> listSaved() {
//        List<SavedArticleEntity> all = savedArticleRepository.findAll();
//        List<SavedArticleDTO> dtoList = new LinkedList<>();
//
//        for (SavedArticleEntity entity : all) {
//            SavedArticleDTO dto1 = new SavedArticleDTO();
//            dto1.setId(entity.getId());
//            dto1.setProfileId(entity.getProfileId());
//            dto1.setArticle(articleService.getArticleShortInfo(entity.getArticleId()));
//            dtoList.add(dto1);
//        }
//        return dtoList;
//     }
}
