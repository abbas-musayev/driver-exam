package com.example.drivingexam.service;

import com.example.drivingexam.dto.response.ExamResponse;
import com.example.drivingexam.model.Exam;

import java.util.List;

public interface ExamService {

    List<ExamResponse> getExam(String username);
    ExamResponse startExam(String username,String sectionName);
}
