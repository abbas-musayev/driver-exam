package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.model.File;
import com.example.drivingexam.model.Question;
import com.example.drivingexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

//    @GetMapping("/getquestions")
//    public ResponseEntity<List<MultipartFile>> getQuestions(String sectionName){
//
//    }

    @PostMapping("/upload")
    public String save(@RequestBody QuestionRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        return questionService.uploadFileToDB(request,file);
    }
    @GetMapping("/download")
    public File downloadFile(@RequestParam Long fileId){
        return questionService.downloadFile(fileId);
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFileResource(@RequestParam Long fileId){
        File file = questionService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment, filename="+file.getFileName())
                .body(new ByteArrayResource(file.getFileData()));
    }



//    @GetMapping("/loadAll")
//    public ResponseEntity<List<FileInfo>> loadALl(){
//        return questionService.loadAll();
//    }




}
