package com.example.controller;

import com.example.dto.SavedArticleDTO;
import com.example.service.SavedArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@RestController
@RequestMapping("/saved_article")
public class SavedArticleController {

    @Autowired private SavedArticleService savedArticleService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody SavedArticleDTO dto){
        SavedArticleDTO response = savedArticleService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody SavedArticleDTO dto){
        Boolean response = savedArticleService.delete(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listSaved(){
        List<SavedArticleDTO> response = savedArticleService.listSaved();
        return ResponseEntity.ok(response);
    }
}
