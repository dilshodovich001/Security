package com.example.exp;

public class RegionAlreadyExistException extends RuntimeException{
    public RegionAlreadyExistException(String message) {
        super(message);
    }
}
