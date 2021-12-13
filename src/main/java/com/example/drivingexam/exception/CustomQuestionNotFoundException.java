package com.example.drivingexam.exception;

public class CustomQuestionNotFoundException extends RuntimeException{
    public CustomQuestionNotFoundException(String message) {
        super(message);
    }
}
