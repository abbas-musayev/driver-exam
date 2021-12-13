package com.example.drivingexam.repo;

import com.example.drivingexam.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {


    @Query("select q from Question q join fetch q.section s where s.id=?1")
    List<Question> getQuestionsBySectionName(String name);

    Question findQuestionByName(String name);
}