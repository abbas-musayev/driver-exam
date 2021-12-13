package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.request.SectionRequest;
import com.example.drivingexam.dto.response.SectionResponse;
import com.example.drivingexam.model.Section;
import com.example.drivingexam.repo.SectionRepo;
import com.example.drivingexam.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepo sectionRepo;
    private final ModelMapper mapper;

    @Override
    public String saveSection(SectionRequest request) {
        Section map = mapper.map(request, Section.class);
        sectionRepo.save(map);
        return "Section Saved";
    }

    @Override
    public List<SectionResponse> getAll() {
        List<Section> all = sectionRepo.findAll();
        List<SectionResponse> response = new ArrayList<>();
        for (Section item : all) {
            response.add(mapper.map(item, SectionResponse.class));
        }
        return response;
    }

    @Override
    public SectionResponse getSectionByName(String name) {
        Section section = sectionRepo.findSectionByName(name);
        SectionResponse map = mapper.map(section, SectionResponse.class);
        return map;
    }
}
