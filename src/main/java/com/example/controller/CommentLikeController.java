package com.example.controller;

import com.example.dto.CommentLikeDTO;
import com.example.dto.article.ArticleLikeDTO;
import com.example.service.ArticleLikeService;
import com.example.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment_like")
public class CommentLikeController {
    @Autowired private CommentLikeService commentLikeService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentLikeDTO dto){
        commentLikeService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CommentLikeDTO dto){
        commentLikeService.updateStatus(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody CommentLikeDTO dto){
         commentLikeService.delete(dto);
        return ResponseEntity.ok().build();
    }
}
