package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto){
        return ResponseEntity.ok(regionService.create(dto));
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Boolean> update(@RequestBody RegionDTO dto,
                                          @PathVariable Integer id){
        return ResponseEntity.ok(regionService.updateById(id,dto));
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(regionService.deleteById(id));
    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<RegionDTO>> get(){
        return ResponseEntity.ok(regionService.getAll());
    }
}
