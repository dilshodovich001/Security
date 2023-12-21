package com.example.service;

import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServise {
    @Autowired
    private ArticleRepository articleRepository;

}
