package com.example.drivingexam.exception;

public class CustomExceptionFileNotFound extends RuntimeException{
    public CustomExceptionFileNotFound(String message) {
        super(message);
    }
}
