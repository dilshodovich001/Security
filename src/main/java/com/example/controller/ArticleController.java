package com.example.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import com.example.dto.article.ArticleCreateDTO;
import com.example.dto.article.ArticleDTO;
import com.example.dto.article.ArticleUpdateDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.mapper.IArticleShortInfoMapper;
import com.example.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<ArticleCreateDTO> create(@RequestBody ArticleCreateDTO dto) {
        log.info("Article create = > "+dto);
        ArticleCreateDTO response = articleService.create(dto);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Integer> update(@PathVariable("id") String id, @RequestBody ArticleUpdateDTO dto){
        log.info("update article -> " + dto +"  id " + id);
        int response = articleService.update(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Integer> delete(@RequestParam("id") String id) {
        log.info("Article delete " + id);
        int response = articleService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/status")
    public ResponseEntity<Integer> status(@RequestParam("id") String id,
                                    @RequestParam("status") ArticleStatus status) {
        log.info("Article status " + id +" " +  status );
        int response = articleService.status(id,status);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/last")
    public ResponseEntity<?> lastFive(@RequestParam("status") String status){
        List<ArticleDTO> response = articleService.lastFive(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/last_three")
    public ResponseEntity<?> lastThree(@RequestParam("status") String status){
        List<ArticleDTO> response = articleService.lastThree(status);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/last_eight")
    public ResponseEntity<List<IArticleShortInfoMapper>> lastEight(@Valid @RequestBody List<String> idList) {
        List<IArticleShortInfoMapper> response = articleService.lastEight(idList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/last_four/{id}")
    public ResponseEntity<?> getLastFour(@PathVariable String id){
        List<IArticleShortInfoMapper> response = articleService.lastFour(id);
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/most_reads")
    public ResponseEntity<?> mostReads() {
        List<IArticleShortInfoMapper> response = articleService.mostReads();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get_five/region/{key}")
    public ResponseEntity<?> getFiveRegion(@PathVariable String key){
        List<IArticleShortInfoMapper> response = articleService.getFiveRegion(key);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_five/region/page")
    public ResponseEntity<?> getListRegionPage(@RequestParam("page") Integer page,
                                               @RequestParam("size") Integer size,@RequestParam("key") String key) {
        Page<IArticleShortInfoMapper> response = articleService.getListRegionPage(page, size,key);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get_five/category/{key}")
    public ResponseEntity<?> getFiveCategory(@PathVariable String key){
        List<IArticleShortInfoMapper> response = articleService.getFiveCategory(key);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_five/category/page")
    public ResponseEntity<Page<IArticleShortInfoMapper>> getListCategoryPage(
            @RequestParam("page") Integer page,@RequestParam("size") Integer size ,
            @RequestParam("key") String key
    ){
        Page<IArticleShortInfoMapper> response = articleService.getListCategoryPage(page, size, key);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view_count/{id}")
    public ResponseEntity<Long> viewCount(@PathVariable("id") String articleId){
        long response = articleService.viewCount(articleId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/share_count/{id}")
    public ResponseEntity<Long> shareCount(@PathVariable("id") String articleId){
        long response = articleService.shareCount(articleId);
        return ResponseEntity.ok(response);
    }





}
