package com.example.controller;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileRoleDTO;
import com.example.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> profileList() {
        List<ProfileDTO> response = profileService.profileList();
        return ResponseEntity.ok(response);
    }

}
