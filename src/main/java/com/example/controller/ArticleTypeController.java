package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.enums.ProfileRole;
import com.example.service.ArticleTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article_type")
public class ArticleTypeController {
    private final ArticleTypeService articleTypeService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody ArticleTypeDTO dto) {
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
    @DeleteMapping(value = "delete/id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
       return ResponseEntity.ok(articleTypeService.delete(id));
    }
}
