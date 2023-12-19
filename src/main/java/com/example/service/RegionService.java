package com.example.service;

import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.exp.RegionAlreadyExistsException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO dto) {
        //TODO bu @Valid anotation bilan qilinadi man lekin hali uni qowmadim qoshib ozgartiramiz oxirida

        if (regionRepository.findByKey(dto.getKey()).isPresent()) {
            throw new RegionAlreadyExistsException("Already exists");
        }
        regionRepository.save(toEntity(dto));

        return dto;
    }
    public Boolean updateById(Integer id, RegionDTO dto){
        int effect = regionRepository.updateById(id, dto.getKey(), dto.getName_uz(), dto.getName_ru(), dto.getName_en());
        return effect == 1;
    }

    public Boolean deleteById(Integer id) {
        int effectedRow = regionRepository.delete(id);
        return effectedRow == 1;
    }

    public List<RegionDTO> getAll() {
        List<RegionEntity> entities = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        entities.forEach(entity -> dtoList.add(toDTO(entity)));

        return dtoList;
    }

    public List<RegionDTO> getByLanguageVisible(LangEnum lang) {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByVisibleTrue().forEach(entity -> dtoList.add(toDTO2(entity,lang)));

        return dtoList;
    }
    private RegionDTO toDTO2(RegionEntity entity, LangEnum lang) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        switch (lang){
            case EN -> dto.setName(entity.getName_en());
            case RU -> dto.setName_ru(entity.getName_ru());
            case UZ -> dto.setName_uz(entity.getName_uz());
            default -> dto.setName(entity.getName_uz());
        }
        return dto;
    }
    private RegionDTO toDTO(RegionEntity entity){
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName_uz(entity.getName_uz());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setVisible(entity.getVisible());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }
    private RegionEntity toEntity(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setKey(dto.getKey());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setName_en(dto.getName_en());
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreated_date(entity.getCreated_date());
        return entity;
    }
}
