package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.response.ExamResponse;
import com.example.drivingexam.model.Exam;
import com.example.drivingexam.model.User;
import com.example.drivingexam.repo.ExamRepo;
import com.example.drivingexam.repo.QuestionRepo;
import com.example.drivingexam.repo.UserRepo;
import com.example.drivingexam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepo examRepo;
    private final UserRepo userRepo;
    private final QuestionRepo questionRepo;
    private final ModelMapper mapper;


    public ExamResponse startExam(String username, String sectionName) {
        Exam exam = new Exam();
        exam.setUser(userRepo.findUserByUsername(username));
        exam.setCreatedDate(LocalDateTime.now());
        exam.setQuestions(questionRepo.getQuestionsBySectionName(sectionName));
        examRepo.save(exam);

        return mapper.map(exam, ExamResponse.class);
    }

    @Override
    public List<ExamResponse> getExam(String username) {
        List<Exam> examByUsername = examRepo.findExamByUsername(username);
        List<ExamResponse> list = new ArrayList<>();

        for (Exam item : examByUsername) {
            list.add(mapper.map(item, ExamResponse.class));
        }
        return list;
    }
}
