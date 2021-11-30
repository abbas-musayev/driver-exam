package com.example.drivingexam.repo;

import com.example.drivingexam.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
