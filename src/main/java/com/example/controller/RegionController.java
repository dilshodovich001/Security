package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping(value = {"","/"})
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto){
        return ResponseEntity.ok(regionService.create(dto));
    }
}
