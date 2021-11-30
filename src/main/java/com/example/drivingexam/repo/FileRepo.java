package com.example.drivingexam.repo;

import com.example.drivingexam.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File,Long> {
}
