package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.CategoryDTO;
import com.example.enums.LangEnum;
import com.example.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        log.info("create category ==> " + dto);
        CategoryDTO response = categoryService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam Integer page,
                                     @RequestParam Integer size) {
        Page<CategoryDTO> response = categoryService.getList(page, size);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") Integer id,
                                         @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateAdmin(id, categoryDTO));
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        int response = categoryService.delete(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/byLang")
    public ResponseEntity<?> getByLang(@RequestParam LangEnum lang) {
        List<CategoryDTO> response = categoryService.getByLang(lang);
        return ResponseEntity.ok(response);

    }


}
