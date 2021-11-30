package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.response.SectionResponse;
import com.example.drivingexam.model.Section;
import com.example.drivingexam.repo.SectionRepo;
import com.example.drivingexam.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepo sectionRepo;
    private final ModelMapper mapper;

    @Override
    public SectionResponse getSectionByName(String name) {
        Section section = sectionRepo.findSectionByName(name);
        SectionResponse map = mapper.map(section, SectionResponse.class);
        return map;
    }
}
