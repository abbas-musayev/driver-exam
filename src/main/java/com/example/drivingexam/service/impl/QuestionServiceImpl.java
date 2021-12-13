package com.example.drivingexam.service.impl;

import com.example.drivingexam.dto.request.QuestionAnswerRequest;
import com.example.drivingexam.dto.request.QuestionRequest;
import com.example.drivingexam.dto.response.QuestionAnswerResponse;
import com.example.drivingexam.dto.response.QuestionResponse;
import com.example.drivingexam.exception.CustomQuestionNotFoundException;
import com.example.drivingexam.model.File;
import com.example.drivingexam.model.Question;
import com.example.drivingexam.model.Section;
import com.example.drivingexam.repo.FileRepo;
import com.example.drivingexam.repo.QuestionRepo;
import com.example.drivingexam.repo.SectionRepo;
import com.example.drivingexam.service.FileService;
import com.example.drivingexam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper mapper;
    private final QuestionRepo questionRepo;
    private final FileRepo fileRepo;
    private final SectionRepo sectionRepo;
    private final FileService fileService;



    // ISLEYIR ANCAQ FILE NULL GEDIR ARADA
    @Override
    public String saveQuestion(QuestionRequest request) throws IOException {

        File file = mapper.map(request.getFile(), File.class);

        Question question = mapper.map(request, Question.class);

        Section section = mapper.map(request.getSection(), Section.class);

        question.setFile(file);
        question.setSection(section);
        question.setAnswer(request.getAnswer());

        questionRepo.save(question);

        return "Saved Question";
    }

    @Override
    public List<QuestionResponse> getAllQuestion() {
        List<Question> all = questionRepo.findAll();
        if (all.isEmpty())
            throw new RuntimeException("GetALL Question IS Empty");
        else {
            List<QuestionResponse> response = new ArrayList<>();
            for (Question item : all) {
                response.add(mapper.map(item, QuestionResponse.class));
            }
            return response;
        }
    }

    @Override
    public QuestionResponse findById(Long id) {
        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new CustomQuestionNotFoundException(""));
        return mapper.map(question, QuestionResponse.class);

    }

    @Override
    public QuestionResponse findByName(String name) {
        Question entity = questionRepo.findQuestionByName(name);
        return mapper.map(entity, QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> getQuestionsBySectionName(String name) {
        List<Question> questions = questionRepo.getQuestionsBySectionName(name);

        List<QuestionResponse> list = new ArrayList<>();
        for (Question item : questions) {
            var map = mapper.map(item, QuestionResponse.class);
            list.add(map);
        }
        return list;
    }

    @Override
    public HashMap<String, String> checkQuestions(List<String> request) {
        HashMap<String, String> response = new HashMap<>();
        for (String item : request) {
            Question question = questionRepo.findQuestionByName(item);
            response.put("questionName", item);
            response.put("answer", question.getAnswer());
        }
        return response;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
