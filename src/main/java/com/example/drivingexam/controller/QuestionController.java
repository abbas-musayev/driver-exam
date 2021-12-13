package com.example.drivingexam.controller;

import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.dto.response.QuestionResponse;
import com.example.drivingexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@RequestBody QuestionRequest request) throws IOException {
        return questionService.saveQuestion(request);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestion();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("getById")
    public QuestionResponse getQuestionById(@RequestParam Long id) {
        return questionService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/bySectionName")
    public List<QuestionResponse> getQuestionsBySectionName(@RequestParam String name) {
        return questionService.getQuestionsBySectionName(name);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/getByName")
    public QuestionResponse findQuestionByName(@RequestParam String name){
        return questionService.findByName(name);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/check-question")
    public HashMap<String, String> checkExam(List<String> request){
        return questionService.checkQuestions(request);
    }


//    @GetMapping("/getFile")
//    public File getFileById(@RequestParam Long fileId){
//        return questionService.getFileBYId(fileId);
//    }

//    // ISLEDIIII
//    @GetMapping("/getFileResource")
//    public ResponseEntity<Resource> downloadFileResourcez(@RequestParam Long fileId){
//        File file = questionService.getFileBYId(fileId);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(file.getFileType()))
//                .body(new ByteArrayResource(file.getFileData()));
//    }

//    //YOXLAA
//    @PostMapping("/uploadLocal")
//    public String uploadFileToLocal(@RequestParam("file") MultipartFile file){
//        return questionService.uploadFileToLocal(file);
//    }

//    // ISLEDII
//    @PostMapping("/saveDB")
//    public void saveDB(@RequestParam("file") MultipartFile file) throws IOException {
//        questionService.uploadFileDB(file);
//    }

}
