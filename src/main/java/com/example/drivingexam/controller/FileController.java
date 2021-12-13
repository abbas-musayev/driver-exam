package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.FileRequest;
import com.example.drivingexam.dto.request.UserRequest;
import com.example.drivingexam.dto.response.FileResponse;
import com.example.drivingexam.model.File;
import com.example.drivingexam.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveFile")
    public void savefile(@RequestBody FileRequest request){
        fileService.saveFile(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveDB")
    public String saveFileToDB(@RequestParam("file")MultipartFile file) throws IOException {
        return fileService.saveFileToDB(file);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveLocal")
    public String saveFileToLocal(@RequestParam("file")MultipartFile file){
        return fileService.saveFileToLocal(file);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // burda file-in name,type,data kimi melumatlari qayidir
    @GetMapping("/getFile")
    public FileResponse getFileById(@RequestParam Long fileId){
        return fileService.findFileById(fileId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getFileResource")
    public ResponseEntity<Resource> downloadFileResourcez(@RequestParam Long fileId){
        FileResponse file = fileService.findFileById(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .body(new ByteArrayResource(file.getFileData()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFileResource(@RequestParam Long fileId){
        FileResponse file = fileService.findFileById(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFileName())
                .body(new ByteArrayResource(file.getFileData()));
    }
}
