package com.example.drivingexam.service;

import com.example.drivingexam.dto.request.QuestionAnswerRequest;
import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.dto.response.QuestionAnswerResponse;
import com.example.drivingexam.dto.response.QuestionResponse;
import com.example.drivingexam.model.Question;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public interface QuestionService {

    String saveQuestion(QuestionRequest request) throws IOException;

    List<QuestionResponse> getAllQuestion();

    QuestionResponse findById(Long id);

    QuestionResponse findByName(String name);

    List<QuestionResponse> getQuestionsBySectionName(String name);


    HashMap<String,String> checkQuestions(List<String> request);

    Stream<Path> loadAll();

}
