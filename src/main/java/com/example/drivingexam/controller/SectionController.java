package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.SectionRequest;
import com.example.drivingexam.dto.response.SectionResponse;
import com.example.drivingexam.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@RequestBody SectionRequest request){
        return service.saveSection(request);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public List<SectionResponse> getAll(){
        return service.getAll();
    }

}
