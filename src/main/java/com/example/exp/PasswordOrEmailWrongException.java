package com.example.exp;

public class PasswordOrEmailWrongException extends RuntimeException {
    public PasswordOrEmailWrongException(String message) {
        super(message);
    }
}
