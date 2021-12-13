package com.example.drivingexam.service;

import com.example.drivingexam.dto.request.FileRequest;
import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void saveFile(FileRequest request);
    String saveFileToDB(MultipartFile file) throws IOException;
    String saveFileToLocal(MultipartFile file);
    FileResponse findFileById(Long id);
}
