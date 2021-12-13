package com.example.drivingexam.exception;

import com.example.drivingexam.exception.response.ExceptionResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptionMy(Exception ex, WebRequest request){
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message,request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomQuestionNotFoundException.class)
    public final ResponseEntity<Object> handleExceptionQuestionNotfound(Exception ex, WebRequest request){
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message,request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomExceptionFileNotFound.class)
    public final ResponseEntity<Object> handleExceptionFileNotFound(Exception ex, WebRequest request){
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message,request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
