package com.example.exp;

public class CategoryAlreadyException extends RuntimeException {
    public CategoryAlreadyException(String message) {
        super(message);
    }
}
