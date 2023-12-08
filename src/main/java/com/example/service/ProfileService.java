package com.example.service;

import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Profile Not Found"));
    }
}
