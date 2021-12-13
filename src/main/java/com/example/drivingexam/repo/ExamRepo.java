package com.example.drivingexam.repo;

import com.example.drivingexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam,Long> {


    @Query("select e from Exam e join fetch e.user u where u.username=?1")
    List<Exam> findExamByUsername(String username);

}
