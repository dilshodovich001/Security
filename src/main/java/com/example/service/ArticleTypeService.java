package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.enums.LangEnum;
import com.example.exp.ArticleTypeAlreadyExitsException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

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
        public int updateAdmin (Long id, ArticleTypeDTO dto){
            ArticleTypeEntity articleTypeEntity = get(id);
            return articleTypeRepository.update(dto.getKey(),
                    dto.getNameEn()
                    , dto.getNameRu(), dto.getNameUz(), articleTypeEntity.getId());
        }

        public ArticleTypeEntity get (Long id){
            return articleTypeRepository.findById(id).
                    orElseThrow(() -> new ItemNotFoundException
                            ("Not Found"));
        }

        public Page<ArticleTypeDTO> getList (Integer page, Integer size){
            Pageable paging = PageRequest.of(page, size);
            Page<ArticleTypeEntity> all = articleTypeRepository.findAll(paging);
            List<ArticleTypeEntity> entities = all.getContent();
            List<ArticleTypeDTO> dtoList = new ArrayList<>();
            for (ArticleTypeEntity entity : entities) {
                ArticleTypeDTO dto1 = new ArticleTypeDTO();
                dto1.setId(entity.getId());
                dto1.setKey(entity.getKey());
                dto1.setNameEn(entity.getNameEn());
                dto1.setNameRu(entity.getNameRu());
                dto1.setNameUz(entity.getNameUz());
                dto1.setCreatedDate(entity.getCreatedDate());

                dtoList.add(dto1);
            }
            return new PageImpl<>(dtoList, paging, all.getTotalElements());
        }

        public List<ArticleTypeDTO> getByLang (LangEnum lang){
            List<ArticleTypeDTO> dtoList = new ArrayList<>();
            for (ArticleTypeEntity articleTypeEntity : articleTypeRepository.findAll()) {
                ArticleTypeDTO dto = new ArticleTypeDTO();
                dto.setId(articleTypeEntity.getId());
                dto.setKey(articleTypeEntity.getKey());
                switch (lang) {
                    case EN -> dto.setName(articleTypeEntity.getNameEn());
                    case RU -> dto.setName(articleTypeEntity.getNameRu());
                    case UZ -> dto.setName(articleTypeEntity.getNameUz());
                }
                dtoList.add(dto);
            }
            return dtoList;

        }

}