package com.example.service;

import com.example.dto.article.ArticleCreateDTO;
import com.example.dto.article.ArticleDTO;
import com.example.dto.article.ArticleUpdateDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ArticleStatus;
import com.example.exp.AlreadyExistsException;
import com.example.exp.ItemNotFoundException;
import com.example.mapper.IArticleShortInfoMapper;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegionService regionService;

    public ArticleCreateDTO create(ArticleCreateDTO dto) {
        if (articleRepository.findByImageId(dto.getImageId()).isPresent()) {
            throw new AlreadyExistsException("Already exists article");
        }
        ArticleEntity entity = toEntity(dto);
        articleRepository.save(entity);
        return dto;
    }

    private ArticleEntity toEntity(ArticleCreateDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId((attachService.getPhotoId(dto.getImageId())));
        entity.setRegionId(regionService.getRegionId(dto.getRegionId()));
        entity.setCategoryId(categoryService.getCategoryId(dto.getCategoryId()));
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        return entity;
    }

    public Integer update(String id, ArticleUpdateDTO dto) {
        if (articleRepository.findById(id).isEmpty()) {
            throw new ItemNotFoundException("Article not found");
        }
        return articleRepository.update(id, dto.getTitle(), dto.getDescription()
                , dto.getContent(), dto.getSharedCount(), dto.getImageId(), dto.getRegionId(), dto.getCategoryId());
    }

    public int delete(String id) {
        return articleRepository.deleteArticle(id);
    }

    public int status(String id, ArticleStatus status) {
        return articleRepository.status(id, status);
    }

    public List<ArticleDTO> lastFive(String status) {
        return articleRepository.lastFive(status).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    private ArticleDTO toDTO(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());

        return dto;
    }

    public List<ArticleDTO> lastThree(String status) {
        return articleRepository.lastThree(status).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<IArticleShortInfoMapper> lastEight(List<String> idList) {
        return articleRepository
                .getTop8Article(ArticleStatus.PUBLISHED.name(), idList);
    }

    public List<IArticleShortInfoMapper> mostReads() {
        return articleRepository.mostReads();
    }

    public List<IArticleShortInfoMapper> lastFour(String id) {
        return articleRepository.getLastFour(id);
    }

    public List<IArticleShortInfoMapper> getFiveRegion(String key) {
        RegionEntity entity = regionService.byKey(key);
        return articleRepository.getByRegionId(entity.getId());
    }


    public Page<IArticleShortInfoMapper> getListRegionPage(Integer page, Integer size, String key) {
        RegionEntity regionEntity = regionService.byKey(key);
        Pageable paging = PageRequest.of(page, size);
        return new PageImpl<>(articleRepository
                .findAllByKey(paging, regionEntity.getId())
                .getContent(), paging,
                articleRepository
                        .findAllByKey(paging, regionEntity.getId())
                        .getTotalElements());
    }


    public List<IArticleShortInfoMapper> getFiveCategory(String key) {
        CategoryEntity entity = categoryService.finByKey(key);
        return articleRepository.getByCategoryId(entity.getId());
    }

    public Page<IArticleShortInfoMapper> getListCategoryPage(Integer page, Integer size, String key) {
        CategoryEntity entity = categoryService.finByKey(key);
        return new PageImpl<>(articleRepository.
                findAllByCategoryKey(PageRequest.of(page, size),
                        entity.getId()).getContent(),
                PageRequest.of(page, size),
                articleRepository
                        .findAllByCategoryKey(PageRequest.of(page, size), entity.getId())
                        .getTotalElements());
    }

    public long viewCount(String id) {
        articleRepository.viewCount(id);
        return articleRepository.viewCountReturn(id);
    }

    public long shareCount(String articleId) {
        articleRepository.shareCount(articleId);
        return articleRepository.shareCountReturn(articleId);
    }

    public ArticleDTO getArticleShortInfo(String id){
        ArticleDTO dto= new ArticleDTO();
        ArticleEntity entity = getEntity(id);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageId(entity.getImageId());
        return dto;
    }
    private ArticleEntity getEntity(String id) {
        return  articleRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Not Found");
        });

    }
}
