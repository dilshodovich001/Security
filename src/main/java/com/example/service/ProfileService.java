package com.example.service;


import com.example.dto.ProfileRoleDTO;
import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
