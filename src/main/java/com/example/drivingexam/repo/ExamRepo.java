package com.example.drivingexam.repo;

import com.example.drivingexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepo extends JpaRepository<Exam,Long> {
}
