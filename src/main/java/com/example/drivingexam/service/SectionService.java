package com.example.drivingexam.service;

import com.example.drivingexam.dto.request.SectionRequest;
import com.example.drivingexam.dto.response.SectionResponse;
import com.example.drivingexam.model.Section;

import java.util.List;

public interface SectionService {

    String saveSection(SectionRequest request);
    List<SectionResponse> getAll();
    SectionResponse getSectionByName(String name);

}
