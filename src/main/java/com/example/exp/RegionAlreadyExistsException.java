package com.example.exp;

public class RegionAlreadyExistsException extends RuntimeException {
    public RegionAlreadyExistsException(String message) {
        super(message);
    }
}
