package com.example.service;


import com.example.dto.auth.AuthRegistrationDTO;
import com.example.dto.auth.AuthRequestDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exp.ItemAlreadyExistException;
import com.example.exp.PasswordOrEmailWrongException;
import com.example.repository.ProfileRepository;
import com.example.util.JwtTokenUtil;
import com.example.util.MD5Util;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@Builder
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;


    public String registration(AuthRegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByPhone(dto.getPhone());

        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                profileRepository.delete(profile);
            } else {
                throw new ItemAlreadyExistException("Profile Already registered");
            }
        }
        ProfileEntity profile = new ProfileEntity();
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setPhone(dto.getPhone());
        profile.setEmail(dto.getEmail());
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setPassword(MD5Util.encode(dto.getPassword()));
        profile.setVisible(Boolean.TRUE);
        profileRepository.save(profile);

        return "Successfully registered";
    }


    public AuthResponseDTO login(AuthRequestDTO dto) {
        ProfileEntity profile = profileRepository.
                findByPhoneAndPassword(dto.getPhone(),
                        MD5Util.encode(dto.getPassword()));
        if (profile == null) {
            throw new PasswordOrEmailWrongException("Password Or Email Wrong");
        }

        return toDTO(profile);
    }

    private AuthResponseDTO toDTO(ProfileEntity profile) {
        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setRole(profile.getRole());
        dto.setToken(JwtTokenUtil.encode(profile.getPhone(), profile.getRole()));
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setRole(profile.getRole());
        return dto;
    }
}
