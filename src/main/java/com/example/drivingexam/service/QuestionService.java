package com.example.drivingexam.service;

import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface QuestionService {

    String uploadFileToLocal(MultipartFile file);
    String uploadFileToDB(QuestionRequest request, MultipartFile file) throws IOException;

    File downloadFile(Long id);

    Stream<Path> loadAll();
}
