package com.example.controller;

import com.example.dto.CommentDTO;
import com.example.mapper.CommentMapperShort;
import com.example.service.CommentService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO dto) {
        log.info("Comment Create -> " + dto);
        CommentDTO response = commentService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{articleId}")
    public ResponseEntity<?> update(@PathVariable("articleId") String articleId, @RequestBody CommentDTO dto) {
        log.info("Comment update " + dto + "  " + articleId);
        CommentDTO response = commentService.update(articleId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Integer commentId) {
        log.info("Comment create: " + commentId);

        String response = commentService.delete(commentId);
        return ResponseEntity.ok(response);
    }


   @GetMapping("/pageComment")
    public ResponseEntity<?> getPageList(@RequestParam("page") int page,@RequestParam int size){
       Page<CommentMapperShort> response = commentService.getPageList(page,size);
       return ResponseEntity.ok(response);
   }

   @GetMapping("/replied/{commentId}")
    public ResponseEntity<?> repliedList(@PathVariable("commentId") Integer commentId){
        List<CommentMapperShort> response =commentService.repliedList(commentId);
        return ResponseEntity.ok(response);
   }

}
