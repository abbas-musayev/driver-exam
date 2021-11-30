package com.example.drivingexam.repo;

import com.example.drivingexam.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepo extends JpaRepository<Section,Long> {
    Section findSectionByName(String name);

}
