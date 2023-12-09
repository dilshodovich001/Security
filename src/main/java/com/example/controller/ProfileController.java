package com.example.controller;


import com.example.dto.ProfileRoleDTO;
import com.example.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/profile_role/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> addProfile(@PathVariable("id") Integer id, @RequestBody ProfileRoleDTO roleDTO) {
        log.info("Add Profile role MODERATOR and PUBLISHER --> " + id);
        int response = profileService.addProfile(id,roleDTO);
        return ResponseEntity.ok(response);
    }
}
