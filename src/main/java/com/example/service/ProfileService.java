package com.example.service;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.dto.ProfileRoleDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exp.AlreadyExistsException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.repository.custom.ProfileCustomRepository;
import com.example.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    @Autowired
    private  ProfileCustomRepository profileCustomRepository;
    @Autowired
    private AttachService attachService;

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Profile Not Found"));
    }


    public List<ProfileDTO> profileList() {
        List<ProfileDTO> dtos = new ArrayList<>();
        for (ProfileEntity profileEntity : profileRepository.findAll()) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profileEntity.getId());
            profileDTO.setName(profileEntity.getName());
            profileDTO.setSurname(profileEntity.getSurname());
            profileDTO.setPhone(profileEntity.getPhone());
            profileDTO.setEmail(profileEntity.getEmail());
            profileDTO.setPassword(profileEntity.getPassword());
            profileDTO.setRole(profileEntity.getRole());
            dtos.add(profileDTO);
        }
        return dtos;
    }

    public int roleCreate(Integer id, ProfileRoleDTO dto) {
        ProfileEntity profile = get(id);
        return profileRepository.updateRole(profile.getId(),dto.getRole());
    }

    public int delete(Integer id) {
        ProfileEntity profile = get(id);
        return profileRepository.update(profile.getId());
    }

    public Page<ProfileFilterDTO> filter(ProfileFilterDTO filterDTO ,int page , int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<ProfileEntity> all = profileCustomRepository.filter(filterDTO, page, size);
        List<ProfileEntity> content = all.getContent();
        List<ProfileFilterDTO> dtos = new LinkedList<>();

        for (ProfileEntity entity : content) {
            ProfileFilterDTO dto = new ProfileFilterDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setEmail(entity.getEmail());
            dtos.add(dto);
        }
        return new PageImpl<>(dtos, paging,all.getTotalElements());

    }

    public ProfileDTO update(ProfileDTO dto) {

        return null;
    }

    public ProfileDTO create(ProfileDTO profileDTO) {
        if (profileRepository.findByPhone(profileDTO.getPhone()).isPresent()) {
            throw new AlreadyExistsException("Phone already exists");
        }
        ProfileEntity entity = toEntity(profileDTO);
        profileRepository.save(entity);
        return profileDTO;
    }
    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setPhone(profileDTO.getPhone());
        entity.setPassword(MD5Util.encode(profileDTO.getPassword()));
        entity.setRole(profileDTO.getRole());
        entity.setVisible(true);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setEmail(profileDTO.getEmail());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPhotoId(attachService.getPhotoId(profileDTO.getPhotoId()));
        return entity;
    }

}
