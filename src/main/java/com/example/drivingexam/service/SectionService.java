package com.example.drivingexam.service;

import com.example.drivingexam.dto.response.SectionResponse;
import com.example.drivingexam.model.Section;

public interface SectionService {

    SectionResponse getSectionByName(String name);
}
