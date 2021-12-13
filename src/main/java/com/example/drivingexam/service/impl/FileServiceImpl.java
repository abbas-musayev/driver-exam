package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.request.FileRequest;
import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.FileResponse;
import com.example.drivingexam.exception.CustomExceptionFileNotFound;
import com.example.drivingexam.model.File;
import com.example.drivingexam.model.User;
import com.example.drivingexam.repo.FileRepo;
import com.example.drivingexam.service.FileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepo fileRepo;
    private final ModelMapper mapper;

    @Override
    public void saveFile(FileRequest request) {
        File map = mapper.map(request, File.class);
        fileRepo.save(map);
    }

    @Override
    public String saveFileToDB(MultipartFile file) throws IOException {
        File uploadFile = new File();

        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileData(file.getBytes());
        uploadFile.setFileType(file.getContentType());
        fileRepo.save(uploadFile);
        return "File Save Olundu";
    }

    @Override
    public String saveFileToLocal(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();

            Path path = Paths.get("F:/" + fileName);
            Files.write(path, file.getBytes());
            return "Saved";
        } catch (IOException e) {
            throw new RuntimeException("error from uploadFileToLocal");
        }
    }

    @Override
    public FileResponse findFileById(Long id) {
        File file = fileRepo.findById(id).orElseThrow(() -> new CustomExceptionFileNotFound("Axtarilan ID(" + id + ")-li File Tapilmadi"));
        return mapper.map(file, FileResponse.class);
    }


}
