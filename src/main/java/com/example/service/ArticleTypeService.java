package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.exp.ArticleTypeAlreadyExitsException;
import com.example.repository.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleTypeService {
    private final ArticleTypeRepository articleTypeRepository;


     public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity byKey = articleTypeRepository.findByKey(dto.getKey());
        if (byKey != null) {
            throw new ArticleTypeAlreadyExitsException("Already exists key");
        }
        ArticleTypeEntity entity = toEntity(dto);
        articleTypeRepository.save(entity);
        return dto;
    }

    private ArticleTypeEntity toEntity(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.getId());
        entity.setKey(dto.getKey());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }


    public Boolean delete(Long id) {
          articleTypeRepository.deleteById(id);
          return true;
    }
}
