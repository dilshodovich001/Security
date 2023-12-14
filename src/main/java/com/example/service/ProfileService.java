package com.example.service;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.dto.ProfileRoleDTO;
import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.repository.custom.ProfileCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    @Autowired
    private  ProfileCustomRepository profileCustomRepository;

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

}
