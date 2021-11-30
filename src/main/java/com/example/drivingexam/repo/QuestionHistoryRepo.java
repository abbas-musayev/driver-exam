package com.example.drivingexam.repo;

import com.example.drivingexam.model.QuestionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionHistoryRepo extends JpaRepository<QuestionHistory,Long> {
}
