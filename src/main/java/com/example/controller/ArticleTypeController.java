package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.enums.LangEnum;
import com.example.service.ArticleTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article_type")
public class ArticleTypeController {
    private final ArticleTypeService articleTypeService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto) {
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
    @DeleteMapping(value = "/delete/id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
       return ResponseEntity.ok(articleTypeService.delete(id));
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") Long id,
                                          @RequestBody ArticleTypeDTO articleTypeDTO
                                        ) {
        return ResponseEntity.ok(articleTypeService.updateAdmin(id, articleTypeDTO));
    }


    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam Integer page,
                                     @RequestParam Integer size) {
        Page<ArticleTypeDTO> response = articleTypeService.getList(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byLang")
    public ResponseEntity<?> getByLang(@RequestParam LangEnum lang) {
        List<ArticleTypeDTO> response = articleTypeService.getByLang(lang);
        return ResponseEntity.ok(response);

    }


}
