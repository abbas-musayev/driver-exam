package com.example.drivingexam.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QuestionHistoryRequest {
    Long id;
    Boolean isTrue;
    String answer;

    QuestionRequest questionDto;

    ExamRequest examDto;
}
