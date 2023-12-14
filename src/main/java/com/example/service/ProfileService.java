package com.example.service;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileRoleDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileEntity get(Long id) {
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

    public int roleCreate(Long id, ProfileRoleDTO dto) {
        ProfileEntity profile = get(id);
        return profileRepository.updateRole(profile.getId(),dto.getRole());
    }

    public Boolean update(Long id, ProfileDTO dto) {

        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        profileRepository.save(entity);
        return true;
    }
}
