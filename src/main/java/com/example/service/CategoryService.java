package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.enums.LangEnum;
import com.example.exp.CategoryAlreadyException;
import com.example.exp.CategoryNotFoundException;
import com.example.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity byKey = categoryRepository.findByKey(dto.getKey());
        if (byKey != null) {
            throw new CategoryAlreadyException("Already exists");
        }
        categoryRepository.save(toEntity(dto));
        return dto;
    }
    private CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setKey(dto.getKey());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDate.now());
        return entity;
    }
    private CategoryDTO toDto(CategoryEntity entity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(entity.getId());
        categoryDTO.setKey(entity.getKey());
        categoryDTO.setNameUz(entity.getNameUz());
        categoryDTO.setNameRu(entity.getNameRu());
        categoryDTO.setNameEn(entity.getNameEn());
        categoryDTO.setCreatedDate(entity.getCreatedDate());
        return categoryDTO;
    }

    public Page<CategoryDTO> getList(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page,size);
        Page<CategoryEntity> all = categoryRepository.findAll(paging);
        List<CategoryEntity> content = all.getContent();
        List<CategoryDTO> dtoList = new ArrayList<>();
        for (CategoryEntity entity : content) {
            CategoryDTO dto =new CategoryDTO();
            dto.setNameRu(entity.getNameRu());
            dto.setNameUz(entity.getNameUz());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setKey(entity.getKey());
            dto.setId(entity.getId());
            dto.setVisible(entity.getVisible());
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, paging, all.getTotalElements());
    }

    public int updateAdmin(Integer id, CategoryDTO dto) {
        return categoryRepository.update(dto.getKey(),
                dto.getNameEn()
                , dto.getNameRu(), dto.getNameUz() ,dto.getVisible(), id);
    }

    public int delete(Integer id) {
        get(id);
      return  categoryRepository.updateId(id);
    }
    public void get(Integer id){
        if (categoryRepository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException("not found");
        }
    }

    public List<CategoryDTO> getByLang(LangEnum lang) {
        List<CategoryDTO> dtoList = new ArrayList<>();
        for (CategoryEntity entity : categoryRepository.findAll()) {
            CategoryDTO dto = new CategoryDTO();
            dto.setKey(entity.getKey());
            dto.setId(entity.getId());
            switch (lang){
                case EN -> dto.setName(entity.getNameEn());
                case RU -> dto.setName(entity.getNameRu());
                case UZ -> dto.setName(entity.getNameUz());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
