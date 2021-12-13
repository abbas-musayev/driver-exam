package com.example.drivingexam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionAnswerResponse {

    QuestionResponse questionResponse;

    String correctAnswer;



}