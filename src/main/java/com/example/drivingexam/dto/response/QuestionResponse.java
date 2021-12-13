package com.example.drivingexam.dto.response;

import com.example.drivingexam.dto.request.FileRequest;
import com.example.drivingexam.dto.request.SectionRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QuestionResponse {
    Long id;
    FileResponse file;
    String name;
    String answer;
    Boolean isCorrect;
    SectionResponse section;
}
