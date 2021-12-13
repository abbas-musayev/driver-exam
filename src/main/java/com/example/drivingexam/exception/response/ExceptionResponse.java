package com.example.drivingexam.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private Date time;
    private String message;
    private String details;


}
