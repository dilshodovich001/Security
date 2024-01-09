package com.example.controller;

import com.example.dto.article.ArticleLikeDTO;
import com.example.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article_like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArticleLikeDTO articleLikeDTO){
     articleLikeService.create(articleLikeDTO);
     return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ArticleLikeDTO dto){
        articleLikeService.updateStatus(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ArticleLikeDTO dto){
        articleLikeService.deleteLike(dto);
        return ResponseEntity.ok().build();
    }


}
