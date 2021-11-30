package com.example.drivingexam.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class QuestionRequest {
    Long id;
    String photo_url;
    String answer;

    SectionRequest sectionDto;
}
