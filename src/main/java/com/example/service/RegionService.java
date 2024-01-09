package com.example.service;

import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.exp.ItemNotFoundException;
import com.example.exp.RegionAlreadyExistException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;


    public RegionDTO create(RegionDTO dto) {
        RegionEntity byKey = regionRepository.findByKey(dto.getKey());
        if (byKey != null) {
            throw new RegionAlreadyExistException("Already exists key");
        }
        RegionEntity entity = toEntity(dto);
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    private RegionEntity toEntity(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setId(dto.getId());
        entity.setKey(dto.getKey());
        entity.setNameEn(dto.getNameEng());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }


    public Page<RegionDTO> getList(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<RegionEntity> all = regionRepository.findAll(paging);
        List<RegionEntity> entities = all.getContent();
        List<RegionDTO> dtoList = new ArrayList<>();
        for (RegionEntity entity : entities) {
            RegionDTO dto1 = new RegionDTO();
            dto1.setId(entity.getId());
            dto1.setKey(entity.getKey());
            dto1.setNameEng(entity.getNameEn());
            dto1.setNameRu(entity.getNameRu());
            dto1.setNameUz(entity.getNameUz());
            dto1.setCreatedDate(entity.getCreatedDate());
            dto1.setVisible(entity.getVisible());

            dtoList.add(dto1);
        }
        return new PageImpl<>(dtoList, paging, all.getTotalElements());
    }

    public int updateAdmin(Integer id, RegionDTO dto) {
        RegionEntity entity = regionRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        return regionRepository.update(dto.getKey(),
                dto.getNameEng()
                , dto.getNameRu(), dto.getNameUz() ,dto.getVisible(), id);
    }


    public Boolean delete(Integer id) {
        regionRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        regionRepository.deleteById(id);
        return true;
    }


    public List<RegionDTO> getByLang(LangEnum lang) {
        List<RegionEntity> entityList = regionRepository.findAllByVisibleTrue();
        List<RegionDTO> dtoList = new ArrayList<>();
        for (RegionEntity entity : entityList) {
            RegionDTO dto = new RegionDTO();
            dto.setKey(entity.getKey());
            dto.setId(entity.getId());
            switch (lang) {
                case UZ -> dto.setName(entity.getNameUz());
                case RU -> dto.setName(entity.getNameRu());
                case EN -> dto.setName(entity.getNameEn());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }


    public RegionEntity get(Integer id){
        return regionRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Not Found"));
    }
    public Integer getRegionId(Integer id){
        if (regionRepository.findById(id).isEmpty()) {
            throw new ItemNotFoundException("Region not found");
        }
        return id;
    }


    public RegionDTO getById(Integer regionId, LangEnum language) {
        RegionEntity entity = get(regionId);
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        switch (language){
            case UZ -> dto.setName(entity.getNameUz());
            case EN -> dto.setName(entity.getNameEn());
            case RU -> dto.setName(entity.getNameRu());
        }
        return dto;
    }

    public RegionEntity byKey(String key) {
        return regionRepository.findByKey(key);
    }
}
