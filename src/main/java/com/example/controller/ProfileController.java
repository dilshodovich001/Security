package com.example.controller;


import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.dto.ProfileRoleDTO;

import com.example.service.ProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> save(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO profile = profileService.create(profileDTO);
        return ResponseEntity.ok(profile);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProfileDTO> update(@RequestBody ProfileDTO dto,
                                             @RequestParam Long id){
        return ResponseEntity.ok(profileService.update(dto));
    }

    @GetMapping("/profile_list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProfileDTO>> profileList() {
        List<ProfileDTO> response = profileService.profileList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile_role/create/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> roleCreate(@PathVariable("id") Integer id,
                                              @RequestBody ProfileRoleDTO dto){
        int response = profileService.roleCreate(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        int result = profileService.delete(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ProfileFilterDTO profileFilterDTO ,
                                    @RequestParam("page") int page
            ,@RequestParam("size") int size){
        Page<ProfileFilterDTO> response = profileService.filter(profileFilterDTO, page, size);
        return ResponseEntity.ok(response);
    }

}
