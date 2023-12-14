package com.example.exp;

public class ArticleTypeAlreadyExitsException extends RuntimeException {
    public ArticleTypeAlreadyExitsException(String message) {
        super(message);
    }
}
