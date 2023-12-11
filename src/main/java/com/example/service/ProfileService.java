package com.example.service;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileRoleDTO;
import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Profile Not Found"));
    }

    public int addProfile(Integer id, ProfileRoleDTO roleDTO) {
        ProfileEntity profile = get(id);
        return  profileRepository.updateRole(roleDTO.getRole() ,profile.getId());
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
}
