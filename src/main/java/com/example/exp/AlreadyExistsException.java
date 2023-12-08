package com.example.exp;

public class AlreadyExistsException extends RuntimeException {
public AlreadyExistsException(String message){
    super(message);
}
}
