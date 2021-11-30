package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.model.File;
import com.example.drivingexam.model.Question;
import com.example.drivingexam.repo.FileRepo;
import com.example.drivingexam.repo.QuestionRepo;
import com.example.drivingexam.repo.SectionRepo;
import com.example.drivingexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final FileRepo fileRepo;
    private final SectionRepo sectionRepo;

    @Override
    public String uploadFileToLocal(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            byte[] data = file.getBytes();
            Path path = Paths.get("/F:" + fileName);
            Files.write(path, data);
            return "Saved";
        } catch (IOException e) {
           throw new RuntimeException("ERRROORR");
        }
    }

    public String uploadFileToDB(QuestionRequest request, MultipartFile file) throws IOException {
        File uploadFile = new File();

        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileData(file.getBytes());
        uploadFile.setFileType(file.getContentType());
        fileRepo.save(uploadFile);

        Question question = Question.builder()
                .file(uploadFile)
                .answer(request.getAnswer())
                .section(sectionRepo.findSectionByName(request.getSectionDto().getName()))
                .build();

        questionRepo.save(question);

        return "Saved Question";

    }

    @Override
    public File downloadFile(Long id) {
        File byId = fileRepo.getById(id);
        return byId;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
