package com.example.drivingexam.controller;

import com.example.drivingexam.dto.response.ExamResponse;
import com.example.drivingexam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get-exam-history")
    public List<ExamResponse> getExam(String username){
        return examService.getExam(username);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/start")
    public ExamResponse start(@RequestParam String username, String sectionName){
        return examService.startExam(username,sectionName);
    }


}
